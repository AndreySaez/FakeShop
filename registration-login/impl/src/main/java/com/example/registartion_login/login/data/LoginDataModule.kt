package com.example.registartion_login.login.data

import android.content.Context
import android.content.SharedPreferences
import com.example.registartion_login.login.data.login.LoginRepositoryImpl
import com.example.registartion_login.login.data.profile.ProfileRepositoryImpl
import com.example.registartion_login.login.data.updateTokens.UpdateTokensRepositoryImpl
import com.example.registartion_login.login.data.updateTokens.workManager.WorkerInitializer
import com.example.registartion_login.login.domain.SessionStorage
import com.example.registartion_login.login.domain.login.LoginRepository
import com.example.registartion_login.login.domain.profile.ProfileRepository
import com.example.registartion_login.login.domain.profile.ProfileUseCaseImpl
import com.example.registartion_login.login.domain.updateTokens.UpdateTokensRepository
import com.example.registartion_login.login.domain.updateTokens.UpdateTokensWorker
import com.example.registration_login_api.profile.ProfileUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(
    includes = [
        LoginDataModule.Declarations::class
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
        abstract fun bindUpdateTokensWorker(updateTokensWorkerImpl: WorkerInitializer): UpdateTokensWorker

        @Binds
        abstract fun bindProfileUseCase(profileUseCaseImpl: ProfileUseCaseImpl): ProfileUseCase
    }
}