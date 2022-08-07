package ru.skillbranch.sbdelivery.presentation.login

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.skillbranch.sbdelivery.domain.usecase.SignInUseCase
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase
): ViewModel() {

    fun signIn(email:String, password: String) = signInUseCase(email, password)
}