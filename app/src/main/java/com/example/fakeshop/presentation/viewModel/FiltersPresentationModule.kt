package com.example.fakeshop.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.fakeshop.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@Module
interface FiltersPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    fun binds(vm: FiltersViewModel): ViewModel

}