package com.example.fakeshop.main_activity

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainActivityModule {
    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindViewModel(impl: MainActivityViewModel): ViewModel
}