package com.example.fakeshop.registration.domain

import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val regRepository: RegistrationRepository
) {
    suspend fun register(registerForm: RegisterForm): RegisterResult {
        val registerRequest = regRepository.registration(registerForm)
        return RegisterResult(registerRequest)
    }
}

data class RegisterResult(
    val newUserId: Int,
)