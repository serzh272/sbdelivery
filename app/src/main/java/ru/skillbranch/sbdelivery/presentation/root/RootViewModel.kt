package ru.skillbranch.sbdelivery.presentation.root

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.onEach
import ru.skillbranch.sbdelivery.data.datasource.LoadResult
import ru.skillbranch.sbdelivery.domain.model.UserModel
import ru.skillbranch.sbdelivery.domain.model.UserModel.Companion.toUserModel
import ru.skillbranch.sbdelivery.domain.usecase.GetProfileUseCase
import ru.skillbranch.sbdelivery.domain.usecase.LogoutUseCase
import javax.inject.Inject

@HiltViewModel
class RootViewModel @Inject constructor(
    private val getProfileUseCase: GetProfileUseCase,
    private val logoutUseCase: LogoutUseCase,
): ViewModel() {
    private val _userData = MutableStateFlow<UserModel?>(null)
    val userData: StateFlow<UserModel?> = _userData

    val profileFlow = getProfileUseCase().filterNotNull().onEach {
        if (it is LoadResult.Success) _userData.value = it.data.toUserModel()
    }
    var isLaunched = false

    suspend fun logout(){
        logoutUseCase()
    }

    fun setUserData(userModel: UserModel){
        _userData.value = userModel
    }
    override fun onCleared() {
        Log.d("M_RootViewModel", "cleared")
        super.onCleared()
    }


}