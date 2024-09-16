package com.example.fakeshop.registration.domain

interface RegistrationRepository {
    suspend fun registration(registerForm: RegisterForm): Boolean
}