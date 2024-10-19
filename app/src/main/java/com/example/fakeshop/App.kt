package com.example.fakeshop

import android.app.Application
import android.content.Context
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.fakeshop.di.AppComponent
import com.example.fakeshop.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {
    lateinit var appComponent: AppComponent

    @Inject
    lateinit var workerFactory: WorkerFactory
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(this)
        appComponent.inject(this)
        WorkManager.initialize(
            this, Configuration.Builder().setWorkerFactory(workerFactory).build()
        )
    }
}

val Context.appComponent: AppComponent
    get() = when (this) {
        is App -> appComponent
        else -> this.applicationContext.appComponent
    }