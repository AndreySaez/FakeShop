package com.example.fakeshop.domain.registration

interface RegistrationRepository {
    suspend fun registration(registerForm: RegisterForm): Boolean
}