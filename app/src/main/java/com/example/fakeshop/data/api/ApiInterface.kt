package com.example.fakeshop.data.api

import com.example.fakeshop.data.login.LoginRequest
import com.example.fakeshop.data.login.LoginState
import com.example.fakeshop.data.registration.RegistrationRequest
import com.example.fakeshop.data.registration.RegistrationState
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("users/auth/login")
    suspend fun logIn(@Body logInRequest: LoginRequest): LoginState

    @POST("users")
    suspend fun registration(@Body createAccountRequest: RegistrationRequest): RegistrationState


    companion object {
        private const val BASE_URL = "https://fakeshopapi-l2ng.onrender.com/app/v1/"
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder()
                .build()
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .baseUrl(BASE_URL)
                .build()
            return retrofit
                .create(ApiInterface::class.java)

        }
    }
}