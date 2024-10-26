package com.example.registartion_login.registration.data

import com.example.coremodule.ApiInterface
import com.example.coremodule.registration.RegistrationRequest
import com.example.registartion_login.registration.domain.RegisterForm
import com.example.registartion_login.registration.domain.RegistrationRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
) : RegistrationRepository {
    override suspend fun registration(registerForm: RegisterForm) = withContext(Dispatchers.IO) {
        apiInterface.registration(
            RegistrationRequest(
                name = registerForm.name,
                email = registerForm.email,
                password = registerForm.password,
            )
        ).id
    }
}