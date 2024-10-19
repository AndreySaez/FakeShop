package com.example.fakeshop.login.data

import android.content.Context
import android.content.SharedPreferences
import com.example.fakeshop.login.data.login.LoginRepositoryImpl
import com.example.fakeshop.login.data.profile.ProfileRepositoryImpl
import com.example.fakeshop.login.data.updateTokens.UpdateTokensRepositoryImpl
import com.example.fakeshop.login.data.updateTokens.workManager.UpdateTokensWorkerImpl
import com.example.fakeshop.login.data.updateTokens.workManager.WorkerBindingModule
import com.example.fakeshop.login.domain.LoginRepository
import com.example.fakeshop.login.domain.ProfileRepository
import com.example.fakeshop.login.domain.SessionStorage
import com.example.fakeshop.login.domain.UpdateTokensRepository
import com.example.fakeshop.login.domain.UpdateTokensWorker
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoginDataModule.Declarations::class,
        WorkerBindingModule::class
    ]
)
class LoginDataModule {

    @Provides
    fun sharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(SessionStorageImpl.PREF_KEY, Context.MODE_PRIVATE)


    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindLoginRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

        @Binds
        abstract fun bindProfileRepository(profileRepositoryImpl: ProfileRepositoryImpl): ProfileRepository

        @Binds
        abstract fun bindSessionStorage(sessionStorageImpl: SessionStorageImpl): SessionStorage

        @Binds
        abstract fun bindUpdateTokensRepository(updateTokensRepositoryImpl: UpdateTokensRepositoryImpl): UpdateTokensRepository

        @Binds
        abstract fun bindUpdateTokensWorker(updateTokensWorkerImpl: UpdateTokensWorkerImpl): UpdateTokensWorker
    }
}