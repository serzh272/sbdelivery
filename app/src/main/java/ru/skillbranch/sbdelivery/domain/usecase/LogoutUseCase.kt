package ru.skillbranch.sbdelivery.domain.usecase

import ru.skillbranch.sbdelivery.data.repository.AuthRepository
import javax.inject.Inject

class LogoutUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke() = repository.logout()
}