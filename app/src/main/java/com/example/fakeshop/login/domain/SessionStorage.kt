package com.example.fakeshop.login.domain

interface SessionStorage {
    suspend fun getSession(): Session
    suspend fun setSession(session: Session)
}

sealed interface Session {
    data class Authorized(
        val accessToken: String,
        val refreshToken: String,
        val expiringTime: Long = 0L
    ): Session {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Authorized

            if (accessToken != other.accessToken) return false
            if (refreshToken != other.refreshToken) return false
            if (expiringTime != other.expiringTime) return false

            return true
        }

        override fun hashCode(): Int {
            var result = accessToken.hashCode()
            result = 31 * result + refreshToken.hashCode()
            result = 31 * result + expiringTime.hashCode()
            return result
        }
    }

    data object UnAuthorized: Session
}