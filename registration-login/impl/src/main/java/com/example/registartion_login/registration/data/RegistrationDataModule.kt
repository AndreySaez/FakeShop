package com.example.registartion_login.registration.data

import com.example.registartion_login.registration.domain.RegistrationRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [RegistrationDataModule.Declarations::class])
class RegistrationDataModule {
    @Provides
    @Singleton
    fun registrationApi(retrofit: Retrofit): RegistrationApi {
        return retrofit.create(RegistrationApi::class.java)
    }

    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(registrationRepositoryImpl: RegistrationRepositoryImpl): RegistrationRepository
    }
}