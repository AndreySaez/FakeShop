package com.example.productslist.presentation.view.productslist

import androidx.lifecycle.ViewModel
import com.example.coremodule.ViewModelKey
import com.example.productslist.presentation.viewModel.ProductListViewModel
import com.example.productslistapi.ProductsListFragmentLauncher
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ProductListPresentationModule {
    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    fun bindProductListViewModel(productListViewModel: ProductListViewModel): ViewModel

    @Binds
    fun bindFragmentLauncher(impl: ProductsListFragmentLauncherImpl): ProductsListFragmentLauncher
}