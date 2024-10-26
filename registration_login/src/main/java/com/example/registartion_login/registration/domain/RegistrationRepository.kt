package com.example.registartion_login.registration.domain

interface RegistrationRepository {
    suspend fun registration(registerForm: RegisterForm): Int
}