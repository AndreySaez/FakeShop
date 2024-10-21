package com.example.fakeshop.login.data.updateTokens.workManager

import android.content.Context
import android.util.Log
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.example.fakeshop.login.domain.UpdateTokensWorker
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class WorkerInitializer @Inject constructor(private val context: Context) : UpdateTokensWorker {
    override fun startTokensUpdatingPeriodicWork() {
        Log.e("Worker","Worker is Initialized")
        val tokenUpdatingBuilder = PeriodicWorkRequest.Builder(
            workerClass = UpdateTokensWorkerImpl::class.java,
            repeatInterval = 9,
            repeatIntervalTimeUnit = TimeUnit.HOURS
        )
        val tokenUpdateConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val tokenUpdateWork = tokenUpdatingBuilder
            .addTag("TOKENS_UPDATE")
            .setConstraints(tokenUpdateConstraints)
            .build()
        WorkManager.getInstance(context).enqueue(tokenUpdateWork)
    }
}