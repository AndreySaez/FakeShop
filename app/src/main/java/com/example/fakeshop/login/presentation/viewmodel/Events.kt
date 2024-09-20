package com.example.fakeshop.login.presentation.viewmodel


sealed interface LoginOneTimeEvent {
    data object NavigateToProductList : LoginOneTimeEvent
    data class MakeErrorToast(val text: Int) : LoginOneTimeEvent
    data object GoToRegistration : LoginOneTimeEvent
}