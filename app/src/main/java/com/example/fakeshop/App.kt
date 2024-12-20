package com.example.fakeshop

import android.app.Application
import android.content.Context
import com.example.coremodule.ComponentProvider
import com.example.fakeshop.di.AppComponent
import com.example.fakeshop.di.DaggerAppComponent

class App : Application(), ComponentProvider<AppComponent> {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override val component: AppComponent
        get() = appComponent
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }