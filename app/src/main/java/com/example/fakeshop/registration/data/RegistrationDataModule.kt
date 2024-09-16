package com.example.fakeshop.registration.data

import com.example.fakeshop.registration.domain.RegistrationRepository
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