package com.example.registartion_login.login.domain.updateTokens

import com.example.registartion_login.login.domain.login.LoginResult

interface UpdateTokensRepository {
    suspend fun updateTokens(refreshToken: RefreshToken): LoginResult
}