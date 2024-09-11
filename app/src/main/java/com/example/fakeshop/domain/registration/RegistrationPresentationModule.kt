package com.example.fakeshop.domain.registration

import androidx.lifecycle.ViewModel
import com.example.fakeshop.presentation.viewModel.RegistrationViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface RegistrationPresentationModule {

    @Binds
    @IntoSet
    fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

}