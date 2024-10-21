package com.example.fakeshop.login.data.updateTokens

import com.example.fakeshop.ApiInterface
import com.example.fakeshop.login.domain.LoginResult
import com.example.fakeshop.login.domain.RefreshToken
import com.example.fakeshop.login.domain.UpdateTokensRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTokensRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : UpdateTokensRepository {
    override suspend fun updateTokens(refreshToken: RefreshToken): LoginResult =
        withContext(Dispatchers.IO) {
            apiInterface.updateTokens(UpdateTokensRequest(refreshToken.refreshToken)).let {
                LoginResult(
                    accessToken = it.accessToken,
                    refreshToken = it.refreshToken
                )
            }
        }
}