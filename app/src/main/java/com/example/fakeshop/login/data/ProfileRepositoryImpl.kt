package com.example.fakeshop.login.data

import android.content.Context
import android.content.SharedPreferences
import com.example.fakeshop.ApiInterface
import com.example.fakeshop.login.domain.ProfileRepository
import com.example.fakeshop.login.domain.ProfileResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    context: Context,
    private val apiInterface: ApiInterface
) : ProfileRepository {
    private val dataStore: SharedPreferences =
        context.getSharedPreferences("TOKEN", Context.MODE_PRIVATE)
    private val TOKEN = "token"
    override suspend fun setToken(token: String) = withContext(Dispatchers.IO) {
        dataStore.edit().putString(TOKEN, "Bearer $token").apply()
    }

    override suspend fun getToken(): String? = withContext(Dispatchers.IO) {
        dataStore.getString(TOKEN, "")
    }

    override suspend fun getProfile(token: String): ProfileResult = withContext(Dispatchers.IO) {
        ProfileResult(apiInterface.getProfile(token).status == "success")
    }

}