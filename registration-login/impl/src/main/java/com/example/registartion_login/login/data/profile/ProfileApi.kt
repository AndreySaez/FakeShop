package com.example.registartion_login.login.data.profile

import com.example.coremodule.profile.ProfileResponse
import retrofit2.http.GET
import retrofit2.http.Header

interface ProfileApi {
    @GET("auth/profile")
    suspend fun getProfile(@Header("Authorization") token: String): ProfileResponse
}