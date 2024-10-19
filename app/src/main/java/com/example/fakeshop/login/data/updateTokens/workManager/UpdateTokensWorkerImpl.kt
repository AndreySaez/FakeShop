package com.example.fakeshop.login.data.updateTokens.workManager

import android.content.Context
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ListenableWorker
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import com.example.fakeshop.login.domain.UpdateSessionUseCase
import com.example.fakeshop.login.domain.UpdateTokensWorker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class UpdateTokensWorkerImpl(
    private val appContext: Context,
    params: WorkerParameters,
    private val updateSessionUseCase: UpdateSessionUseCase
) : CoroutineWorker(appContext, params), UpdateTokensWorker {
    override suspend fun doWork(): Result {
        withContext(Dispatchers.IO) {
            try {
                updateSessionUseCase.updateTokens()
            } catch (e: Exception) {
                return@withContext Result.retry()
            }
        }
        return Result.success()
    }

    override fun startTokensUpdatingPeriodicWork() {
        val tokenUpdatingBuilder = PeriodicWorkRequest.Builder(
            workerClass = UpdateTokensWorkerImpl::class.java,
            repeatInterval = 15,
            repeatIntervalTimeUnit = TimeUnit.MINUTES
        )
        val tokenUpdateConstraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
        val tokenUpdateWork = tokenUpdatingBuilder
            .addTag("TOKENS_UPDATE")
            .setConstraints(tokenUpdateConstraints)
            .build()
        WorkManager.getInstance(appContext).enqueue(tokenUpdateWork)
    }
}

class Factory @Inject constructor(
    private val updateSessionUseCase: UpdateSessionUseCase
) : ChildWorkerFactory {
    override fun create(appContext: Context, params: WorkerParameters): ListenableWorker {
        return UpdateTokensWorkerImpl(
            appContext,
            params,
            updateSessionUseCase
        )
    }

}