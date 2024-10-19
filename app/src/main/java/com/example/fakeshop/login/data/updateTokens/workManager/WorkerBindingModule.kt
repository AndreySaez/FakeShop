package com.example.fakeshop.login.data.updateTokens.workManager

import androidx.work.WorkerFactory
import com.example.fakeshop.di.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerBindingModule {
    @Binds
    @IntoMap
    @WorkerKey(UpdateTokensWorkerImpl::class)
    fun bindUpdateTokensWorker(factory: Factory): ChildWorkerFactory

    @Binds
    @IntoMap
    @WorkerKey(UpdateTokensWorkerImpl::class)
    fun bindWorkerFactory(updateTokensWorkerFactory: UpdateTokensWorkerFactory): WorkerFactory
}