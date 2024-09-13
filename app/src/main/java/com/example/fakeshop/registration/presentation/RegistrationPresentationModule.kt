package com.example.fakeshop.registration.presentation

import androidx.lifecycle.ViewModel
import com.example.fakeshop.di.ViewModelKey
import com.example.fakeshop.registration.presentation.viewmodel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface RegistrationPresentationModule {

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

}