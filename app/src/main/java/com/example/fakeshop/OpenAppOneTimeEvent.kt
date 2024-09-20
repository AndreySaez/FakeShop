package com.example.fakeshop

sealed interface OpenAppOneTimeEvent {
    data object NavigateToLoginFragment:OpenAppOneTimeEvent
    data object NaviGateToProductListFragment:OpenAppOneTimeEvent
}