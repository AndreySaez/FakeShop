package com.example.fakeshop.login.data

import com.example.fakeshop.login.domain.LoginRepository
import dagger.Binds
import dagger.Module

@Module(includes = [LoginDataModule.Declarations::class])
interface LoginDataModule {

    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository
    }
}