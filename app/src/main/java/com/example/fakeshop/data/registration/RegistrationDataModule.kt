package com.example.fakeshop.data.registration

import com.example.fakeshop.domain.registration.RegistrationRepository
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