package com.example.registartion_login

import com.example.coremodule.AppRouter
import com.example.coremodule.ContextProvider
import com.example.coremodule.RetrofitModule
import com.example.registartion_login.login.data.LoginDataModule
import com.example.registartion_login.login.data.updateTokens.workManager.UpdateTokensWorkerImpl
import com.example.registartion_login.login.presentation.LoginPresentationModule
import com.example.registartion_login.login.presentation.view.LoginFragment
import com.example.registartion_login.registration.data.RegistrationDataModule
import com.example.registartion_login.registration.presentation.RegistrationPresentationModule
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import dagger.Component


@Component(
    modules = [
        LoginPresentationModule::class,
        LoginDataModule::class,
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        RetrofitModule::class
    ], dependencies = [ContextProvider::class, AppRouter::class]
)
interface RegistrationLoginComponent {
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)
    fun inject(worker: UpdateTokensWorkerImpl)
}