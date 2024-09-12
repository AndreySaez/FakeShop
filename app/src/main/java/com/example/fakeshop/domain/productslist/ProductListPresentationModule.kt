package com.example.fakeshop.domain.productslist

import androidx.lifecycle.ViewModel
import com.example.fakeshop.di.ViewModelKey
import com.example.fakeshop.presentation.viewModel.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import dagger.multibindings.IntoSet

@Module
interface ProductListPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    fun bindProductListViewModel(productListViewModel: ProductListViewModel): ViewModel
}