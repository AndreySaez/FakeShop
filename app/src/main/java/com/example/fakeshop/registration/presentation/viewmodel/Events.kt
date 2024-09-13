package com.example.fakeshop.registration.presentation.viewmodel

sealed interface RegistrationOneTimeEvent {
    data class MakeErrorToast(val text: Int) : RegistrationOneTimeEvent
    data object NavigateToLogin : RegistrationOneTimeEvent
}