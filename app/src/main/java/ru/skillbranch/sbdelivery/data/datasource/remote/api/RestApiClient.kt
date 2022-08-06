package ru.skillbranch.sbdelivery.data.datasource.remote.api

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch
import retrofit2.Response
import ru.skillbranch.sbdelivery.data.datasource.local.datastore.PreferenceManager
import ru.skillbranch.sbdelivery.data.datasource.remote.api.ApiResponse.Companion.ApiErrorType
import ru.skillbranch.sbdelivery.data.datasource.remote.api.interceptor.requestWithAuthentication
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.RefreshTokenDto
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.UnknownHostException
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

internal typealias ApiServiceRequest<T> = suspend SBDeliveryApi.() -> Response<T>

class RestApiClient @Inject constructor(
    private val api: SBDeliveryApi,
    private val preferenceManager: PreferenceManager,
) {

    companion object {

        private val <T> Response<T>.data: T
            get() = body() ?: throw Exception()

        @Suppress("NOTHING_TO_INLINE")
        private inline fun Response<*>.needUpdateAccessToken(): Boolean {
            return raw().request.requestWithAuthentication() && code() == HttpURLConnection.HTTP_UNAUTHORIZED
        }
    }

    internal suspend fun <T : Any> use(apiServiceRequest: ApiServiceRequest<T>): ApiResponse<T> = internalUse(true, apiServiceRequest)

    private suspend fun <T : Any> internalUse(retryRequest: Boolean, apiServiceRequest: ApiServiceRequest<T>): ApiResponse<T> {
        return try {
            val response = apiServiceRequest(api)

            when {
                response.isSuccessful -> ApiResponse.Success(response.data)
                retryRequest && response.needUpdateAccessToken() -> refreshAccessTokenAndRetryRequest(apiServiceRequest)
                else -> ApiResponse.Failed(message = response.message(), errorCode = response.code())
            }
        } catch (e: UnknownHostException) {
            ApiResponse.HttpException(ApiErrorType.UNKNOWN_HOST_EXCEPTION, e)
        } catch (e: ConnectException) {
            ApiResponse.HttpException(ApiErrorType.CONNECTION_EXCEPTION, e)
        } catch (e: Exception) {
            ApiResponse.HttpException(ApiErrorType.SERVICE_UNAVAILABLE, e)
        }
    }

    private val refreshingAccessToken = AtomicBoolean(false)

    private suspend fun <T : Any> refreshAccessTokenAndRetryRequest(apiServiceRequest: ApiServiceRequest<T>): ApiResponse<T> = coroutineScope {
        launch {
            if (refreshingAccessToken.compareAndSet(false, true)) try {
                val currentRefreshToken = preferenceManager.refreshTokenFlow.firstOrNull() ?: ""
                val apiResponse = use {
                    api.refreshToken(refreshTokenDto = RefreshTokenDto(currentRefreshToken))
                }

                val newAccessToken = when (apiResponse) {
                    is ApiResponse.Success -> apiResponse.data.accessToken
                    is ApiResponse.Failed -> throw Exception()
                    is ApiResponse.HttpException -> throw Exception()
                }

                preferenceManager.updateAccessToken(newAccessToken)
            } finally {
                refreshingAccessToken.set(false)
            }
        }.join()

        // This Magic call. Retrying request happen without without repeatedly call, what throw exception when failure.
        internalUse(false, apiServiceRequest)
    }
}


