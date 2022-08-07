package ru.skillbranch.sbdelivery.domain.model

import ru.skillbranch.sbdelivery.data.datasource.remote.api.response.ProfileInfoDto

data class UserModel(
    val firstName: String,
    val lastName: String,
    val email: String,
){
    companion object{

        fun ProfileInfoDto.toUserModel(): UserModel{
            return UserModel(
                firstName = firstName,
                lastName = lastName,
                email = email
            )
        }
    }
}
