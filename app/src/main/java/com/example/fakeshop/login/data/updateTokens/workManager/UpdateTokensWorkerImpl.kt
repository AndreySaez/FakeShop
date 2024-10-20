package com.example.fakeshop.login.data.updateTokens.workManager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.fakeshop.appComponent
import com.example.fakeshop.login.domain.UpdateSessionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateTokensWorkerImpl @Inject constructor(
    appContext: Context,
    params: WorkerParameters,
) : CoroutineWorker(appContext, params) {

    @Inject
    lateinit var updateSessionUseCase: UpdateSessionUseCase

    init {
        appContext.appComponent.inject(this)
    }

    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            Log.e("Worker","Worker is Initialized")
            try {
                updateSessionUseCase.updateTokens()
            } catch (e: Exception) {
                return@withContext Result.retry()
            }
        }
        return Result.success()
    }
}