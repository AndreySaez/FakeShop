package com.example.fakeshop.registration.data

import com.example.coremodule.ApiInterface
import com.example.coremodule.registration.RegistrationRequest
import com.example.fakeshop.registration.domain.RegisterForm
import com.example.fakeshop.registration.domain.RegistrationRepository
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