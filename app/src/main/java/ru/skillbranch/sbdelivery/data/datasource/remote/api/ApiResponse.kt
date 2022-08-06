package ru.skillbranch.sbdelivery.data.datasource.remote.api

import androidx.annotation.StringRes
import ru.skillbranch.sbdelivery.R

sealed class ApiResponse<out T : Any> {

    companion object{
        enum class ApiErrorType(@StringRes errorTypeRes: Int){
            SERVER_ERROR(R.string.server_error),
            UNKNOWN_HOST_EXCEPTION(R.string.unknown_host_error),
            CONNECTION_EXCEPTION(R.string.connection_error),
            SERVICE_UNAVAILABLE(R.string.service_unavailable),
        }
    }
    data class Success<T : Any>(
        val data: T,
    ) : ApiResponse<T>()

    data class Failed(
        val message: String,
        val errorCode: Int,
    ) : ApiResponse<Nothing>()

    data class HttpException(
        val type: ApiErrorType,
        val e: Exception,
    ) : ApiResponse<Nothing>()
}