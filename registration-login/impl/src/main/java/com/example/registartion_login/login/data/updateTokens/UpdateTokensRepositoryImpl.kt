package com.example.registartion_login.login.data.updateTokens

import com.example.coremodule.ApiInterface
import com.example.coremodule.updateTokens.UpdateTokensRequest
import com.example.registartion_login.login.domain.login.LoginResult
import com.example.registartion_login.login.domain.updateTokens.RefreshToken
import com.example.registartion_login.login.domain.updateTokens.UpdateTokensRepository
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