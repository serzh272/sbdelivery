package ru.skillbranch.sbdelivery.data.repository

import ru.skillbranch.sbdelivery.data.datasource.local.datastore.PreferenceManager
import ru.skillbranch.sbdelivery.data.datasource.remote.api.ApiResponse
import ru.skillbranch.sbdelivery.data.datasource.remote.api.RestApiClient
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.RegisterDataDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.SignInDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.response.ProfileInfoDto
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val restApiClient: RestApiClient,
    private val preferenceManager: PreferenceManager,
) {

    suspend fun getProfile(): ApiResponse<ProfileInfoDto>{
        return restApiClient.use {
            getProfile()
        }
    }

    suspend fun signIn(email: String, password: String): ApiResponse<ProfileInfoDto> {
        return restApiClient.use {
            login(SignInDto(email = email, password = password))
        }.also {
            if (it is ApiResponse.Success){
                preferenceManager.updateAccessToken(it.data.accessToken)
                preferenceManager.updateRefreshToken(it.data.refreshToken)
            }
        }
    }

    suspend fun logout() {
        preferenceManager.clearTokens()
    }

    suspend fun signUp(firstName: String, lastName: String, email: String, password: String): ApiResponse<ProfileInfoDto> {
        return restApiClient.use {
            register(RegisterDataDto(
                firstName = firstName,
                lastName = lastName,
                email = email,
                password = password))
        }.also {
            if (it is ApiResponse.Success){
                preferenceManager.updateAccessToken(it.data.accessToken)
                preferenceManager.updateRefreshToken(it.data.refreshToken)
            }
        }
    }
}