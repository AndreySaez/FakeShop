package com.example.registartion_login.registration

import android.content.Context
import com.example.coremodule.ApiInterfaceModule
import com.example.registartion_login.registration.data.RegistrationDataModule
import com.example.registartion_login.registration.presentation.RegistrationPresentationModule
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import com.example.registration_login_api.login.LoginDependenciesProvider
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        ApiInterfaceModule::class
    ], dependencies = [LoginDependenciesProvider::class]
)
interface RegistrationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            loginDependency: LoginDependenciesProvider
        ): RegistrationComponent
    }

    fun inject(fragment: RegistrationFragment)
}