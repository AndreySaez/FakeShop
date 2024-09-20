package com.example.fakeshop.login.domain

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {

    suspend fun login(loginForm: LoginForm): LoginResult {
        return loginRepository.login(loginForm)
    }
}

data class LoginResult(
    val isLoginSuccess: Boolean,
    val token: String
)