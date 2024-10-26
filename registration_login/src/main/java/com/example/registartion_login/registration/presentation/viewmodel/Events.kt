package com.example.registartion_login.registration.presentation.viewmodel

sealed interface RegistrationOneTimeEvent {
    data class MakeErrorToast(val text: Int) : RegistrationOneTimeEvent
    data object NavigateToLogin : RegistrationOneTimeEvent
}