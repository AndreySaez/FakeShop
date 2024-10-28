package com.example.registartion_login.login.domain.login

import com.example.registartion_login.login.domain.Session
import com.example.registartion_login.login.domain.SessionStorage
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val loginRepository: LoginRepository,
    private val sessionStorage: SessionStorage
) {

    suspend fun login(loginForm: LoginForm) {
        val login = loginRepository.login(loginForm)
        sessionStorage.setSession(
            Session.Authorized(
                accessToken = login.accessToken,
                refreshToken = login.refreshToken
            )
        )
    }
}

data class LoginResult(
    val accessToken: String,
    val refreshToken:String
)