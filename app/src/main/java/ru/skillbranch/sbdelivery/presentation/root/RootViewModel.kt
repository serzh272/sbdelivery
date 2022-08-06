package ru.skillbranch.sbdelivery.presentation.root

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.skillbranch.sbdelivery.domain.usecase.GetProfileUseCase
import ru.skillbranch.sbdelivery.domain.usecase.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
): ViewModel() {
    val profileFlow = getProfileUseCase()
    var isAuthorized = false

    suspend fun logout(){
        logoutUseCase()
        isAuthorized = false
    }
    override fun onCleared() {
        Log.d("M_RootViewModel", "cleared")
        super.onCleared()
    }


}