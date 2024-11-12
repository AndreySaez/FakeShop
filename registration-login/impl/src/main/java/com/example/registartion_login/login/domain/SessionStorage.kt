package com.example.registartion_login.login.domain

interface SessionStorage {
    suspend fun getSession(): Session
    suspend fun setSession(session: Session)
}

sealed interface Session {
    data class Authorized(
        val accessToken: String,
        val refreshToken: String,
        val expiringTime: Int = 9
    ) : Session

    data object UnAuthorized : Session
}