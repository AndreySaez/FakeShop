package com.example.fakeshop.presentation.viewModel.action

sealed interface RegistrationAction {
    data object EnterClick : RegistrationAction
    data class OnNameChanged(val nameValue: String) : RegistrationAction
    data class OnEmailChanged(val emailValue: String) : RegistrationAction
    data class OnPasswordChanged(val passwordValue: String) : RegistrationAction
    data class OnCpasswordChanged(val cpasswordValue: String) : RegistrationAction

}

sealed interface LoginAction {
    data object EnterClick : LoginAction
    data class OnEmailChanged(val emailValue: String) : LoginAction
    data class OnPasswordChanged(val passwordValue: String) : LoginAction

}