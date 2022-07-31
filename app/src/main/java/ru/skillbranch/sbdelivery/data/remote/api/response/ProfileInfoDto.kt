package ru.skillbranch.sbdelivery.data.remote.api.response

data class ProfileInfoDto(
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val accessToken: String? = null,
    val refreshToken: String? = null
)