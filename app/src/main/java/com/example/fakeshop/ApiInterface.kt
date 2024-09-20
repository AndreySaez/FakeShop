package com.example.fakeshop

import com.example.fakeshop.login.data.LoginRequest
import com.example.fakeshop.login.data.LoginState
import com.example.fakeshop.login.data.ProfileState
import com.example.fakeshop.productlist.data.list.ProductList
import com.example.fakeshop.registration.data.RegistrationRequest
import com.example.fakeshop.registration.data.RegistrationState
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterface {
    @GET("products")
    suspend fun productList(
        @Query("limit") limit: String = "20",
        @Query("page") page: String = "1",
        @Query("category") category: String? = null,
        @Query("sort") sort: String? = null
    ): ProductList

    @POST("users/auth/login")
    suspend fun logIn(@Body logInRequest: LoginRequest): LoginState

    @GET("users/auth/login/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileState

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