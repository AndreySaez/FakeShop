package com.example.coremodule

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

abstract class BaseViewModel<Action, State, Events>(initialState: State) : ViewModel() {

    val state: StateFlow<State> get() = _state.asStateFlow()
    private val _state = MutableStateFlow(initialState)

    val oneTimeEvents get() = _oneTimeEvents.asSharedFlow()
    private val _oneTimeEvents = MutableSharedFlow<Events>()

    abstract fun onAction(action: Action)

    protected fun setState(state: State) {
        _state.value = state
    }

    protected suspend fun setEvent(event: Events) {
        _oneTimeEvents.emit(event)
    }
}