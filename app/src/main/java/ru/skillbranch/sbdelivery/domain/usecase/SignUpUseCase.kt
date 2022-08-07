package ru.skillbranch.sbdelivery.domain.usecase

import kotlinx.coroutines.flow.flow
import ru.skillbranch.sbdelivery.data.datasource.LoadResult
import ru.skillbranch.sbdelivery.data.datasource.remote.api.ApiResponse
import ru.skillbranch.sbdelivery.data.repository.AuthRepository
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    operator fun invoke(firstName: String, lastName: String, email: String, password: String) = flow {
        emit(LoadResult.Load())
        when(val resp = repository.signUp(firstName, lastName, email, password)){
            is ApiResponse.Success -> {
                emit(LoadResult.Success(resp.data))
            }
            is ApiResponse.Failed -> {
                emit(LoadResult.Error(resp.message + resp.errorCode))
            }
            is ApiResponse.HttpException -> {
                emit(LoadResult.Error(resp.e.localizedMessage ?: "${resp.type}"))
            }
        }
    }
}