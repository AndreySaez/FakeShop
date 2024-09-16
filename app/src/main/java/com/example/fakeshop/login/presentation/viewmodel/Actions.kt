package com.example.fakeshop.login.presentation.viewmodel

sealed interface LoginAction {
    data object EnterClick : LoginAction
    data class OnEmailChanged(val emailValue: String) : LoginAction
    data class OnPasswordChanged(val passwordValue: String) : LoginAction

}