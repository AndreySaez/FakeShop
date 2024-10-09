package com.example.fakeshop.login.data

import android.content.Context
import android.content.SharedPreferences
import com.example.fakeshop.login.domain.LoginRepository
import com.example.fakeshop.login.domain.ProfileRepository
import com.example.fakeshop.login.domain.SessionStorage
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [LoginDataModule.Declarations::class])
class LoginDataModule {

    @Provides
    fun sharedPreference(context: Context): SharedPreferences =
        context.getSharedPreferences(SessionStorageImpl.PREF_KEY, Context.MODE_PRIVATE)


    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

        @Binds
        abstract fun bindProfileRepository(profileRepository: ProfileRepositoryImpl): ProfileRepository

        @Binds
        abstract fun bindSessionStorage(impl: SessionStorageImpl): SessionStorage
    }
}