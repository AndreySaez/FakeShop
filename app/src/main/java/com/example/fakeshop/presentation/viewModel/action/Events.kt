package com.example.fakeshop.presentation.viewModel.action

sealed interface RegistrationOneTimeEvent {
    data class MakeErrorToast(val text: Int) : RegistrationOneTimeEvent
    data object NavigateToLogin : RegistrationOneTimeEvent
}

sealed interface LoginOneTimeEvent {
    data class MakeErrorToast(val text: Int) : LoginOneTimeEvent
}