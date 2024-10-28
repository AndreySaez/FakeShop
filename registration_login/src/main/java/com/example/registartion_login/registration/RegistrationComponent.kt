package com.example.registartion_login.registration

import com.example.coremodule.RetrofitModule
import com.example.registartion_login.registration.data.RegistrationDataModule
import com.example.registartion_login.registration.presentation.RegistrationPresentationModule
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import dagger.Component


@Component(
    modules = [
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        RetrofitModule::class
    ]
)
interface RegistrationComponent {
    fun inject(fragment: RegistrationFragment)
}