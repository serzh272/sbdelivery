package ru.skillbranch.sbdelivery.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.skillbranch.sbdelivery.domain.usecase.SignUpUseCase
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val signUpUseCase: SignUpUseCase
) : ViewModel() {

    fun signUp(
        firstName: String,
        lastName: String,
        email: String,
        password: String
    ) = signUpUseCase(firstName, lastName, email, password)
}