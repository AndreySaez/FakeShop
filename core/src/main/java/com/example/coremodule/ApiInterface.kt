package com.example.coremodule

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


interface ApiInterface {


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