package com.example.fakeshop.main_activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registartion_login.login.domain.profile.ProfileUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val profileUseCase: ProfileUseCase
) : ViewModel() {
    val mainEventFlow get() = _mainEventFlow.asSharedFlow()
    private val _mainEventFlow = MutableSharedFlow<OpenAppOneTimeEvent>()
    fun getProfile() {
        viewModelScope.launch {
            try {
                profileUseCase.getProfile()
                _mainEventFlow.emit(OpenAppOneTimeEvent.NaviGateToProductListFragment)
            } catch (e: Exception) {
                _mainEventFlow.emit(OpenAppOneTimeEvent.NavigateToLoginFragment)
            }
        }
    }
}