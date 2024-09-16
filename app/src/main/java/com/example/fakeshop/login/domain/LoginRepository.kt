package com.example.fakeshop.login.domain

interface LoginRepository {
    suspend fun login(loginForm: LoginForm): LoginResult
}