package com.example.registartion_login.registration

import android.content.Context
import com.example.coremodule.RetrofitModule
import com.example.registartion_login.registration.data.RegistrationDataModule
import com.example.registartion_login.registration.presentation.RegistrationPresentationModule
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import dagger.BindsInstance
import dagger.Component


@Component(
    modules = [
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        RetrofitModule::class
    ]
)
interface RegistrationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
        ): RegistrationComponent
    }

    fun inject(fragment: RegistrationFragment)
}