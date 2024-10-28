package com.example.coremodule.app

import android.app.Application
import android.content.Context
import com.example.coremodule.AppRouter


abstract class BaseApp : Application() {

    abstract fun provideAppRouter(): AppRouter
}

val Context.productsBaseApp get() = applicationContext as BaseApp