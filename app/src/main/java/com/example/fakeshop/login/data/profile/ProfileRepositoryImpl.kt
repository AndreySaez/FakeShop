package com.example.fakeshop.login.data.profile

import com.example.fakeshop.ApiInterface
import com.example.fakeshop.login.domain.ProfileRepository
import com.example.fakeshop.login.domain.ProfileResult
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