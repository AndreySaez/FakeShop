package com.example.fakeshop.data.login

import com.example.fakeshop.domain.login.LoginRepository
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