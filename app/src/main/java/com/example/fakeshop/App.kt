package com.example.fakeshop

import android.content.Context
import com.example.coremodule.AppRouter
import com.example.coremodule.app.BaseApp
import com.example.fakeshop.di.AppComponent
import com.example.fakeshop.di.DaggerAppComponent

class App : BaseApp() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
    }

    override fun provideAppRouter(): AppRouter = appComponent.appRouter
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }