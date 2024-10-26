package com.example.fakeshop.productlist.presentation.view.productslist

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import com.example.fakeshop.productlist.presentation.viewModel.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductListPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    fun bindProductListViewModel(productListViewModel: ProductListViewModel): ViewModel
}