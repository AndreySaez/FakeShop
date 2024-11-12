package com.example.registartion_login.login.data

import android.content.SharedPreferences
import com.example.registartion_login.login.domain.Session
import com.example.registartion_login.login.domain.SessionStorage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SessionStorageImpl @Inject constructor(
    private val preferences: SharedPreferences
) : SessionStorage {

    override suspend fun getSession(): Session = withContext(Dispatchers.IO) {
        val access = preferences.getString(ACCESS_TOKEN, "")
        val refresh = preferences.getString(REFRESH_TOKEN, "")
        if (access != null && refresh != null) {
            Session.Authorized(
                accessToken = access,
                refreshToken = refresh
            )
        } else {
            Session.UnAuthorized
        }
    }

    override suspend fun setSession(session: Session) = withContext(Dispatchers.IO) {
        when (session) {
            is Session.Authorized -> {
                preferences.edit().putString(ACCESS_TOKEN, "Bearer ${session.accessToken}").apply()
                preferences.edit().putString(REFRESH_TOKEN, session.refreshToken).apply()
            }

            is Session.UnAuthorized -> {
                preferences.edit().putString(ACCESS_TOKEN, null).apply()
                preferences.edit().putString(REFRESH_TOKEN, null).apply()
            }
        }
    }

    companion object {
        private const val ACCESS_TOKEN = "access_token"
        private const val REFRESH_TOKEN = "refresh_token"
        const val PREF_KEY = "ACCESS_TOKEN"
    }
}