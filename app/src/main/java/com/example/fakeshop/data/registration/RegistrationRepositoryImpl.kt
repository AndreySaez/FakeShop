package com.example.fakeshop.data.registration

import com.example.fakeshop.data.api.ApiInterface
import com.example.fakeshop.domain.registration.RegisterForm
import com.example.fakeshop.domain.registration.RegistrationRepository
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
                confirmedPassword = registerForm.cpassword
            )
        ).status == "success"
    }
}