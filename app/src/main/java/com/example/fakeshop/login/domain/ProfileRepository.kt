package com.example.fakeshop.login.domain

interface ProfileRepository {
    suspend fun getProfile(token: String): ProfileResult
}