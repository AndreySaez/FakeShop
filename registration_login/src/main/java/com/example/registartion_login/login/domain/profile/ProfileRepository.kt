package com.example.registartion_login.login.domain.profile

interface ProfileRepository {
    suspend fun getProfile(token: String): ProfileResult
}