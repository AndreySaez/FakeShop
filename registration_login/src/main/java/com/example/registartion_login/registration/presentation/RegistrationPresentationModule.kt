package com.example.registartion_login.registration.presentation

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import com.example.registartion_login.registration.presentation.view.RegistrationFragmentLauncherImpl
import com.example.registartion_login.registration.presentation.viewmodel.RegistrationViewModel
import com.example.registration_login_api.registration.RegistrationFragmentLauncher
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RegistrationPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    fun bindRegistrationFragmentLauncher(impl: RegistrationFragmentLauncherImpl): RegistrationFragmentLauncher

}