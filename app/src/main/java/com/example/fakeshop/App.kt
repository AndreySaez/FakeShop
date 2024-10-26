package com.example.fakeshop

import android.app.Application
import android.content.Context
import com.example.coremodule.ContextProvider
import com.example.fakeshop.di.AppComponent
import com.example.fakeshop.di.DaggerAppComponent

class App : Application(), ContextProvider {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)

    }

    override fun provideContext(): Context {
        return applicationContext
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }