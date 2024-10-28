package com.example.coremodule

import com.example.coremodule.productlist.Product
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object Navigator {
    val state get() = _state.asStateFlow()
    private val _state = MutableStateFlow(NavigationState.DEFAULT)
    fun openProduct(product: Product) {
        _state.tryEmit(NavigationState(product))
    }

    fun closeDetails() {
        _state.tryEmit(NavigationState.DEFAULT)
    }
}

data class NavigationState(val product: Product? = null) {
    companion object {
        val DEFAULT = NavigationState()
    }
}