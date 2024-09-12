package com.example.fakeshop.domain.registration

import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val regRepository: RegistrationRepository
) {
    suspend fun register(registerForm: RegisterForm): RegisterResult {
        val registerRequest = regRepository.registration(registerForm)
        return RegisterResult(registerRequest.status == "success")
    }
}

data class RegisterResult(
    val isRegisterSuccess: Boolean,
)