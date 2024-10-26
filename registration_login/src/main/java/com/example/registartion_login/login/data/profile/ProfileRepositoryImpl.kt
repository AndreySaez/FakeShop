package com.example.registartion_login.login.data.profile

import com.example.coremodule.ApiInterface
import com.example.registartion_login.login.domain.ProfileRepository
import com.example.registartion_login.login.domain.ProfileResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : ProfileRepository {

    override suspend fun getProfile(token: String): ProfileResult = withContext(Dispatchers.IO) {
        ProfileResult(apiInterface.getProfile(token).id)
    }
}