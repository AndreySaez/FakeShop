package com.example.fakeshop.registration.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeshop.R
import com.example.fakeshop.registration.domain.RegisterForm
import com.example.fakeshop.registration.domain.RegisterUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : ViewModel() {

    val state get() = _state.asStateFlow()
    private val _state = MutableStateFlow(RegisterState.INITIAL)

    val eventFlow get() = _eventFlow.asSharedFlow()
    private val _eventFlow = MutableSharedFlow<RegistrationOneTimeEvent>()

    fun onAction(action: RegistrationAction) {
        when (action) {
            is RegistrationAction.EnterClick -> submitRegisterForm()
            is RegistrationAction.OnNameChanged -> changeName(action.nameValue)
            is RegistrationAction.OnEmailChanged -> changeEmail(action.emailValue)
            is RegistrationAction.OnPasswordChanged -> changePassword(action.passwordValue)
            is RegistrationAction.haveAccountClicked -> alreadyHaveAccount()
        }
    }

    private fun alreadyHaveAccount() {
        viewModelScope.launch {
            _eventFlow.emit(RegistrationOneTimeEvent.NavigateToLogin)
        }
    }

    private fun changeName(nameValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(name = nameValue)
        _state.value = currentState.copy(registerForm = newForm)
    }

    private fun changeEmail(emailValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(email = emailValue)
        _state.value = currentState.copy(registerForm = newForm)
        validateForm()
    }

    private fun changePassword(passwordValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(password = passwordValue)
        _state.value = currentState.copy(registerForm = newForm)
        validateForm()
    }

    private fun validateForm() {
        var currentState = state.value
        currentState = currentState.copy(
            isPasswordWrong = (
                    currentState.registerForm
                        .password.length !in 8..24
                    ),
            isEmailWrong = (
                    !currentState.registerForm.email
                        .matches(android.util.Patterns.EMAIL_ADDRESS.toRegex())
                    )
        )
        _state.value = currentState
    }

    private fun submitRegisterForm() {
        viewModelScope.launch {
            val currentState = state.value
            try {
                _state.value = currentState.copy(isLoading = true)
                registerUseCase.register(state.value.registerForm)
                _eventFlow.emit(RegistrationOneTimeEvent.NavigateToLogin)
            } catch (e: Exception) {
                _eventFlow.emit(RegistrationOneTimeEvent.MakeErrorToast(R.string.something_wrong))
            } finally {
                _state.value = currentState.copy(isLoading = false)
            }
        }
    }
}

data class RegisterState(
    val registerForm: RegisterForm,
    val isLoading: Boolean = false,
    val isPasswordWrong: Boolean = false,
    val isEmailWrong: Boolean = false,
) {
    companion object {
        val INITIAL = RegisterState(
            RegisterForm("", "", "")
        )
    }
}