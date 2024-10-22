package com.example.coremodule

import com.example.coremodule.login.LoginRequest
import com.example.coremodule.login.LoginResponse
import com.example.coremodule.productlist.CategoryResponse
import com.example.coremodule.productlist.ProductDTO
import com.example.coremodule.profile.ProfileResponse
import com.example.coremodule.registration.RegistrationRequest
import com.example.coremodule.registration.RegistrationState
import com.example.coremodule.updateTokens.UpdateTokensRequest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiInterface {
    @GET("products")
    suspend fun getProductList(
        @Query("price_min") priceMin: Int? = null,
        @Query("price_max") priceMax: Int? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") page: Int = 0,
        @Query("categoryId") categoryId: Int? = null
    ): List<ProductDTO>

    @GET("categories")
    suspend fun getCategories(
        @Query("id") id: Int? = null
    ): List<CategoryResponse>

    @POST("auth/login")
    suspend fun logIn(@Body logInRequest: LoginRequest): LoginResponse

    @GET("auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse

    @POST("users")
    suspend fun registration(@Body createAccountRequest: RegistrationRequest): RegistrationState

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