package com.example.registartion_login.registration.data

import com.example.registartion_login.registration.domain.RegistrationRepository
import dagger.Binds
import dagger.Module

@Module(includes = [RegistrationDataModule.Declarations::class])
interface RegistrationDataModule {

    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository
    }
}