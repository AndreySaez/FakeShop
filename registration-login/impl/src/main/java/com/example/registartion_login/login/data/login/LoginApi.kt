package com.example.registartion_login.login.data.login

import com.example.coremodule.login.LoginRequest
import com.example.coremodule.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginApi {
    @POST("auth/login")
    suspend fun logIn(@Body logInRequest: LoginRequest): LoginResponse
}