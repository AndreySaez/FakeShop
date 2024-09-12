package com.example.fakeshop.data.login

import com.example.fakeshop.data.api.ApiInterface
import com.example.fakeshop.domain.login.LoginForm
import com.example.fakeshop.domain.login.LoginRepository
import com.example.fakeshop.domain.login.LoginResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : LoginRepository {
    override suspend fun login(loginForm: LoginForm) = withContext(Dispatchers.IO) {
        apiInterface.logIn(LoginRequest(loginForm.email, loginForm.password)).let {
            LoginResult(
                it.status == "success",
                it.token,
            )
        }
    }
}