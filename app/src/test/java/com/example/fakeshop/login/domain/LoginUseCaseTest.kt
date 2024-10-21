package com.example.fakeshop.login.domain

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.doThrow
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LoginUseCaseTest {
    private lateinit var loginRepository: LoginRepository
    private lateinit var sessionStorage: SessionStorage
    private lateinit var loginUseCase: LoginUseCase

    @BeforeEach
    fun setUp() {
        loginRepository = mock()
        sessionStorage = mock()
        loginUseCase = LoginUseCase(loginRepository, sessionStorage)
    }

    @Test
    fun `login should store session when login is successful`() = runBlocking {
        val loginForm = LoginForm("username", "password")
        val expectedLoginResponse = LoginResult("accessToken", "refreshToken")

        whenever(loginRepository.login(loginForm)).thenReturn(expectedLoginResponse)

        loginUseCase.login(loginForm)

        verify(sessionStorage).setSession(
            Session.Authorized(
                accessToken = expectedLoginResponse.accessToken,
                refreshToken = expectedLoginResponse.refreshToken
            )
        )
    }

    @Test
    fun `login should throw exception when login fails`(): Unit = runBlocking {
        val loginForm = LoginForm("username", "wrongPassword")

        doThrow(RuntimeException("Login failed")).whenever(loginRepository).login(loginForm)

        val exception = assertThrows<RuntimeException> {
            loginUseCase.login(loginForm)
        }

        assertEquals("Login failed", exception.message)
    }
}