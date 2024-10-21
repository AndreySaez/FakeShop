package com.example.fakeshop.main_activity

sealed interface OpenAppOneTimeEvent {
    data object NavigateToLoginFragment: OpenAppOneTimeEvent
    data object NaviGateToProductListFragment: OpenAppOneTimeEvent
}