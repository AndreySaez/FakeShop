package com.example.registartion_login.login.domain

interface LoginRepository {
    suspend fun login(loginForm: LoginForm): LoginResult
}