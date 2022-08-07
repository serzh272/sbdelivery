package ru.skillbranch.sbdelivery.data.datasource.remote.api.request

data class RegisterDataDto(
    val firstName: String,
    val lastName: String,
    val email: String,
    val password: String
)