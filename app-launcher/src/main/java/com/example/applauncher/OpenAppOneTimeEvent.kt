package com.example.applauncher

sealed interface OpenAppOneTimeEvent {
    data object NavigateToLoginFragment: OpenAppOneTimeEvent
    data object NaviGateToProductListFragment: OpenAppOneTimeEvent
}