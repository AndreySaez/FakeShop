package com.example.registartion_login.registration.data

import com.example.coremodule.registration.RegistrationRequest
import com.example.coremodule.registration.RegistrationState
import retrofit2.http.Body
import retrofit2.http.POST

interface RegistrationApi {
    @POST("users")
    suspend fun registration(@Body createAccountRequest: RegistrationRequest): RegistrationState
}