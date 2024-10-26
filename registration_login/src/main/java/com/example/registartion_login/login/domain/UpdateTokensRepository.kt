package com.example.registartion_login.login.domain

interface UpdateTokensRepository {
    suspend fun updateTokens(refreshToken: RefreshToken): LoginResult
}