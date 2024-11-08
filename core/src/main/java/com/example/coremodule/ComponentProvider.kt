package com.example.coremodule

import android.content.Context

interface ComponentProvider<T> {
    val component: T
}

inline fun <reified T> Context.findDependency(): T {
    val component = (applicationContext as ComponentProvider<*>).component
    return component as? T
        ?: throw IllegalStateException("AppContext does not implemented ${T::class}")
}