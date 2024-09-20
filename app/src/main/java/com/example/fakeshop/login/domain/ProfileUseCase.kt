package com.example.fakeshop.login.domain

import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository
) {
    suspend fun getProfile(token: String): ProfileResult {
        return profileRepository.getProfile(token)
    }

    suspend fun setToken(token: String) {
        profileRepository.setToken(token)
    }

    suspend fun getToken(): String? = profileRepository.getToken()

}

data class ProfileResult(
    val getProfileSuccess: Boolean
)