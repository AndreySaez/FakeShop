package com.example.fakeshop.productlist.presentation.viewModel

import androidx.lifecycle.ViewModel
import com.example.fakeshop.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FiltersPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(FiltersViewModel::class)
    fun bindFiltersViewModel(filtersViewModel: FiltersViewModel): ViewModel

}