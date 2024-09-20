package com.example.fakeshop.login.data

import com.example.fakeshop.login.domain.LoginRepository
import com.example.fakeshop.login.domain.ProfileRepository
import dagger.Binds
import dagger.Module

@Module(includes = [LoginDataModule.Declarations::class])
interface LoginDataModule {

    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(loginRepositoryImpl: LoginRepositoryImpl): LoginRepository

        @Binds
        abstract fun bindProfileRepository(profileRepository: ProfileRepositoryImpl):ProfileRepository
    }
}