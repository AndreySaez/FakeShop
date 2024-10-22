package com.example.fakeshop.login.data.login

import com.example.coremodule.ApiInterface
import com.example.coremodule.login.LoginRequest
import com.example.fakeshop.login.domain.LoginForm
import com.example.fakeshop.login.domain.LoginRepository
import com.example.fakeshop.login.domain.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : LoginRepository {

    override suspend fun login(loginForm: LoginForm) = withContext(Dispatchers.IO) {
        apiInterface.logIn(LoginRequest(loginForm.email, loginForm.password))
            .let {
                LoginResult(
                    it.accessToken,
                    it.refreshToken
                )
            }
    }
}