package com.example.fakeshop.login.domain

interface ProfileRepository {
    suspend fun setToken(token: String)
    suspend fun getToken(): String?
    suspend fun getProfile(token: String): ProfileResult
}