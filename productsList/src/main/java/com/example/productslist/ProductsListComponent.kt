package com.example.productslist

import android.content.Context
import com.example.coremodule.AppRouter
import com.example.coremodule.RetrofitModule
import com.example.productslist.data.category.CategoriesDataModule
import com.example.productslist.data.list.ProductListDataModule
import com.example.productslist.presentation.view.filters.FiltersFragment
import com.example.productslist.presentation.view.productslist.ProductListPresentationModule
import com.example.productslist.presentation.view.productslist.ProductsListFragment
import com.example.productslist.presentation.viewModel.FiltersPresentationModule
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RetrofitModule::class,
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
    ], dependencies = [AppRouter::class]
)
interface ProductsListComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appRouter: AppRouter
        ): ProductsListComponent
    }

    fun inject(fragment: ProductsListFragment)
    fun inject(fragment: FiltersFragment)
}