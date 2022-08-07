package ru.skillbranch.sbdelivery.data.datasource.remote.api

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.RefreshTokenDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.RegisterDataDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.request.SignInDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.response.AccessTokenDto
import ru.skillbranch.sbdelivery.data.datasource.remote.api.response.ProfileInfoDto

interface AuthApi {

    @POST("auth/register")
    suspend fun register(@Body registerData: RegisterDataDto): Response<ProfileInfoDto>

    @POST("auth/login")
    suspend fun login(@Body signInDto: SignInDto): Response<ProfileInfoDto>

    @POST("auth/refresh")
    suspend fun refreshToken(
        //@Header("Content-Type") contentType:String = "application/json",
        @Body refreshTokenDto: RefreshTokenDto
    ): Response<AccessTokenDto>
}