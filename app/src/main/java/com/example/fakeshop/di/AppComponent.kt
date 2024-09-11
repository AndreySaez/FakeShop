package com.example.fakeshop.di

import com.example.fakeshop.data.api.ApiInterface
import com.example.fakeshop.data.login.LoginDataModule
import com.example.fakeshop.data.registration.RegistrationDataModule
import com.example.fakeshop.domain.login.LoginPresentationModule
import com.example.fakeshop.domain.registration.RegistrationPresentationModule
import com.example.fakeshop.presentation.view.login.LoginFragment
import com.example.fakeshop.presentation.view.registration.RegistrationFragment
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(
    modules = [
        AppModule::class,
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        LoginPresentationModule::class,
        LoginDataModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)
}

@Module
class AppModule {
    @Provides
    fun apiInterface() = ApiInterface.create()
}
