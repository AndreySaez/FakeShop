package com.example.fakeshop.domain.login

import com.example.fakeshop.data.login.LoginState

interface LoginRepository {
    suspend fun login(loginForm: LoginForm): LoginResult
}