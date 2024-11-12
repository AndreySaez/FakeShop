package com.example.registartion_login.login.domain.profile

import com.example.registartion_login.login.domain.Session
import com.example.registartion_login.login.domain.SessionStorage
import com.example.registration_login_api.profile.ProfileResult
import com.example.registration_login_api.profile.ProfileUseCase
import javax.inject.Inject

class ProfileUseCaseImpl @Inject constructor(
    private val profileRepository: ProfileRepository,
    private val sessionStorage: SessionStorage
):ProfileUseCase {
    override suspend fun getProfile(): ProfileResult {
        val session = sessionStorage.getSession() as? Session.Authorized
            ?: throw IllegalStateException("No token")
        return profileRepository.getProfile(session.accessToken)
    }
}
