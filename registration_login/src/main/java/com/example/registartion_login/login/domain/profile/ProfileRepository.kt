package com.example.registartion_login.login.domain.profile

import com.example.registration_login_api.profile.ProfileResult

interface ProfileRepository {
    suspend fun getProfile(token: String): ProfileResult
}