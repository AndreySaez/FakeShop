package com.example.registartion_login.login.domain.login

interface LoginRepository {
    suspend fun login(loginForm: LoginForm): LoginResult
}