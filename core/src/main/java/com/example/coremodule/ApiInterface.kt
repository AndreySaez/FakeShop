package com.example.coremodule

import com.example.coremodule.login.LoginResponse
import com.example.coremodule.profile.ProfileResponse
import com.example.coremodule.updateTokens.UpdateTokensRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface ApiInterface {
    @GET("auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse

    @POST("auth/refresh-token")
    suspend fun updateTokens(@Body refreshTokensRequest: UpdateTokensRequest): LoginResponse


    companion object {
        private const val BASE_URL = "https://api.escuelajs.co/api/v1/"
        private val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        fun create(): ApiInterface {
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
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