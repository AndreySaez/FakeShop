package com.example.fakeshop.domain.login

import androidx.lifecycle.ViewModel
import com.example.fakeshop.presentation.viewModel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface LoginPresentationModule {
    @Binds
    @IntoSet
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}