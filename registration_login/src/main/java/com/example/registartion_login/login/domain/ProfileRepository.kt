package com.example.registartion_login.login.domain

interface ProfileRepository {
    suspend fun getProfile(token: String): ProfileResult
}