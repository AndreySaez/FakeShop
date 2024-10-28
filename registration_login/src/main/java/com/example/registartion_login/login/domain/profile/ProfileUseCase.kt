package com.example.registartion_login.login.domain.profile

import com.example.registartion_login.login.domain.Session
import com.example.registartion_login.login.domain.SessionStorage
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val sessionStorage: SessionStorage
) {
    suspend fun getProfile(): ProfileResult {
        val session = sessionStorage.getSession() as? Session.Authorized
            ?: throw IllegalStateException("No token")
        return profileRepository.getProfile(session.accessToken)
    }
}

data class ProfileResult(
    val profileId: Int
)