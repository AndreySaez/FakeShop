package com.example.registartion_login.registration.presentation.viewmodel

sealed interface RegistrationAction {
    data object haveAccountClicked : RegistrationAction
    data object EnterClick : RegistrationAction
    data class OnNameChanged(val nameValue: String) : RegistrationAction
    data class OnEmailChanged(val emailValue: String) : RegistrationAction
    data class OnPasswordChanged(val passwordValue: String) : RegistrationAction

}