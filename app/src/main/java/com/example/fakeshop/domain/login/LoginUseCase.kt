package com.example.fakeshop.domain.login

import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository
) {
    suspend fun login(loginForm: LoginForm): LoginResult {
        val loginReguest = loginRepository.login(loginForm)
        return (LoginResult(
            isLoginSuccess = loginReguest.status == "success",
            token = loginReguest.token
        ))
    }
}

data class LoginResult(
    val isLoginSuccess: Boolean,
    val token: String
)