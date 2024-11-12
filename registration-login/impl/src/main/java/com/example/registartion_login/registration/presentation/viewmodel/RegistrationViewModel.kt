package com.example.registartion_login.registration.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.example.coremodule.BaseViewModel
import com.example.registartion_login.R
import com.example.registartion_login.registration.domain.RegisterForm
import com.example.registartion_login.registration.domain.RegisterUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
) : BaseViewModel<RegistrationAction, RegisterState, RegistrationOneTimeEvent>(RegisterState.INITIAL) {
    override fun onAction(action: RegistrationAction) {
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
            setEvent(RegistrationOneTimeEvent.NavigateToLogin)
        }
    }

    private fun changeName(nameValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(name = nameValue)
        setState(currentState.copy(registerForm = newForm))
    }

    private fun changeEmail(emailValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(email = emailValue)
        setState(currentState.copy(registerForm = newForm))
        validateForm()
    }

    private fun changePassword(passwordValue: String) {
        val currentState = state.value
        val newForm = currentState.registerForm.copy(password = passwordValue)
        setState(currentState.copy(registerForm = newForm))
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
        setState(currentState)
    }

    private fun submitRegisterForm() {
        viewModelScope.launch {
            val currentState = state.value
            try {
                setState(currentState.copy(isLoading = true))
                registerUseCase.register(state.value.registerForm)
                setEvent(RegistrationOneTimeEvent.NavigateToLogin)
            } catch (e: Exception) {
                setEvent(RegistrationOneTimeEvent.MakeErrorToast(R.string.something_wrong))
            } finally {
                setState(currentState.copy(isLoading = false))
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