package com.example.fakeshop.domain.registration

import com.example.fakeshop.data.registration.RegistrationState

interface RegistrationRepository {
    suspend fun registration(registerForm: RegisterForm): RegistrationState
}