package com.example.registartion_login.registration.presentation

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import com.example.registartion_login.registration.presentation.viewmodel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
internal interface RegistrationPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

}