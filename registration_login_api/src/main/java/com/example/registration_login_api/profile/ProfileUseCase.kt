package com.example.registration_login_api.profile

interface ProfileUseCase {
    suspend fun getProfile(): ProfileResult
}

data class ProfileResult(
    val profileId: Int
)