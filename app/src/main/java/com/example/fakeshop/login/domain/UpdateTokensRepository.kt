package com.example.fakeshop.login.domain

interface UpdateTokensRepository {
    suspend fun updateTokens(refreshToken: RefreshToken): LoginResult
}