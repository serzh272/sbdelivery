package ru.skillbranch.sbdelivery.data.remote.api

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import ru.skillbranch.sbdelivery.data.remote.api.request.RegisterDataDto
import ru.skillbranch.sbdelivery.data.remote.api.response.AccessTokenDto
import ru.skillbranch.sbdelivery.data.remote.api.response.ProfileInfoDto

interface AuthApi {

    @POST("auth/register")
    suspend fun register(registerData: RegisterDataDto): Response<ProfileInfoDto>

    @FormUrlEncoded
    @POST("auth/login")
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
    ): Response<ProfileInfoDto>

    @FormUrlEncoded
    @POST("auth/refresh")
    suspend fun refreshToken(
        @Field("refreshToken") refreshToken: String
    ): Response<AccessTokenDto>
}