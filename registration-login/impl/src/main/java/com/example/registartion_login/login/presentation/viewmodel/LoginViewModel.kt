package com.example.registartion_login.login.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.coremodule.BaseViewModel
import com.example.registartion_login.R
import com.example.registartion_login.login.domain.login.LoginForm
import com.example.registartion_login.login.domain.login.LoginUseCase
import com.example.registartion_login.login.domain.updateTokens.UpdateTokensWorker
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val updateTokensWorker: UpdateTokensWorker
) : BaseViewModel<LoginAction, LoginState, LoginOneTimeEvent>(LoginState.INITIAL) {

    override fun onAction(action: LoginAction) {
        when (action) {
            LoginAction.EnterClick -> submitLoginForm()
            is LoginAction.OnEmailChanged -> changeEmail(action.emailValue)
            is LoginAction.OnPasswordChanged -> changePassword(action.passwordValue)
            LoginAction.DontHaveAccount -> haveNoAccount()
        }
    }


    private fun haveNoAccount() {
        viewModelScope.launch {
            setEvent(LoginOneTimeEvent.GoToRegistration)
        }
    }

    private fun submitLoginForm() {
        viewModelScope.launch {
            val currentState = state.value
            try {
                setState(currentState.copy(isLoading = true))
                loginUseCase.login(state.value.loginForm)
                setEvent(LoginOneTimeEvent.NavigateToProductList)
                updateTokensWorker.startTokensUpdatingPeriodicWork()
            } catch (e: Exception) {
                setEvent(LoginOneTimeEvent.MakeErrorToast(R.string.something_wrong))
            } finally {
                setState(currentState.copy(isLoading = false))
            }
        }
    }

    private fun changePassword(passwordValue: String) {
        val currentState = state.value
        val newForm = currentState.loginForm.copy(password = passwordValue)
        setState(currentState.copy(loginForm = newForm))
    }

    private fun changeEmail(emailValue: String) {
        val currentState = state.value
        val newForm = currentState.loginForm.copy(email = emailValue)
        setState(currentState.copy(loginForm = newForm))
    }
}

data class LoginState(
    val loginForm: LoginForm,
    val isLoading: Boolean = false
) {
    companion object {
        val INITIAL = LoginState(LoginForm("", ""))
    }
}

