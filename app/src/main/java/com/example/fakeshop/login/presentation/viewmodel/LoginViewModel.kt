package com.example.fakeshop.login.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeshop.R
import com.example.fakeshop.login.domain.LoginForm
import com.example.fakeshop.login.domain.LoginUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {
    val state get() = _state.asStateFlow()
    private val _state = MutableStateFlow(LoginState.INITIAL)

    val eventFlow get() = _eventFlow.asSharedFlow()
    private val _eventFlow = MutableSharedFlow<LoginOneTimeEvent>()

    fun onAction(action: LoginAction) {
        when (action) {
            is LoginAction.EnterClick -> submitLoginForm()
            is LoginAction.OnEmailChanged -> changeEmail(action.emailValue)
            is LoginAction.OnPasswordChanged -> changePassword(action.passwordValue)
            is LoginAction.DontHaveAccount -> haveNoAccount()
        }
    }

    private fun haveNoAccount() {
        viewModelScope.launch {
            _eventFlow.emit(LoginOneTimeEvent.GoToRegistration)
        }
    }

    private fun submitLoginForm() {
        viewModelScope.launch {
            val currentState = state.value
            try {
                _state.value = currentState.copy(isLoading = true)
                loginUseCase.login(state.value.loginForm)
                _eventFlow.emit(LoginOneTimeEvent.NavigateToProductList)
            } catch (e: Exception) {
                _eventFlow.emit(LoginOneTimeEvent.MakeErrorToast(R.string.something_wrong))
            } finally {
                _state.value = currentState.copy(isLoading = false)
            }
        }
    }

    private fun changePassword(passwordValue: String) {
        val currentState = state.value
        val newForm = currentState.loginForm.copy(password = passwordValue)
        _state.value = currentState.copy(loginForm = newForm)
    }

    private fun changeEmail(emailValue: String) {
        val currentState = state.value
        val newForm = currentState.loginForm.copy(email = emailValue)
        _state.value = currentState.copy(loginForm = newForm)
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

