package com.example.productslist

import android.content.Context
import com.example.coremodule.RetrofitModule
import com.example.productslist.data.category.CategoriesDataModule
import com.example.productslist.data.list.ProductListDataModule
import com.example.productslist.presentation.view.filters.FiltersFragment
import com.example.productslist.presentation.view.productslist.ProductListPresentationModule
import com.example.productslist.presentation.view.productslist.ProductsListFragment
import com.example.productslist.presentation.viewModel.FiltersPresentationModule
import com.example.prosuctdetailsapi.ProductDetailsDependenciesProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
        RetrofitModule::class
    ], dependencies = [ProductDetailsDependenciesProvider::class]
)
@Singleton
interface ProductsListComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            dependencies: ProductDetailsDependenciesProvider
        ): ProductsListComponent
    }

    fun inject(fragment: ProductsListFragment)
    fun inject(fragment: FiltersFragment)
}