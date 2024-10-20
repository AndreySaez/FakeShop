package com.example.fakeshop.login.domain

class UpdateSessionUseCase(
    private val repository: UpdateTokensRepository,
    private val sessionStorage: SessionStorage
) {
    var refreshToken = RefreshToken("")
    suspend fun updateTokens() {
        val currentSession = sessionStorage.getSession()
        if (currentSession is Session.Authorized) {
            val currentRefreshToken = currentSession.refreshToken
            val newLoginResult =
                repository.updateTokens(refreshToken.copy(refreshToken = currentRefreshToken))
            sessionStorage.setSession(
                Session.Authorized(
                    accessToken = newLoginResult.accessToken,
                    refreshToken = newLoginResult.refreshToken
                )
            )
        } else throw IllegalStateException("No refresh token, User is not authorized")
    }
}