package com.example.registartion_login.login.data.updateTokens

import com.example.coremodule.login.LoginResponse
import com.example.coremodule.updateTokens.UpdateTokensRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface UpdateTokensApi {
    @POST("auth/refresh-token")
    suspend fun updateTokens(@Body refreshTokensRequest: UpdateTokensRequest): LoginResponse
}