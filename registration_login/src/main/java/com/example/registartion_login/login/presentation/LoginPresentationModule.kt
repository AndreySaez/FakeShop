package com.example.registartion_login.login.presentation

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import com.example.registartion_login.login.presentation.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface LoginPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel
}