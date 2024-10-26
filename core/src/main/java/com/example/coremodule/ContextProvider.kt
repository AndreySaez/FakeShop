package com.example.coremodule

import android.content.Context

interface ContextProvider {
    fun provideContext():Context
}