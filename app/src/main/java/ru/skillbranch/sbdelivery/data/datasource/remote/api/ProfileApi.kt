package ru.skillbranch.sbdelivery.data.datasource.remote.api

import retrofit2.Response
import retrofit2.http.GET
import ru.skillbranch.sbdelivery.data.datasource.remote.api.response.ProfileInfoDto

interface ProfileApi {

    @GET("profile")
    suspend fun getProfile(): Response<ProfileInfoDto>
}