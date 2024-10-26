package com.example.fakeshop.di

import android.content.Context
import com.example.coremodule.AppRouter
import com.example.coremodule.RetrofitModule
import com.example.fakeshop.AppRouterImpl
import com.example.fakeshop.main_activity.MainActivity
import com.example.fakeshop.main_activity.MainActivityModule
import com.example.fakeshop.productlist.data.category.CategoriesDataModule
import com.example.fakeshop.productlist.data.list.ProductListDataModule
import com.example.fakeshop.productlist.presentation.view.filters.FiltersFragment
import com.example.fakeshop.productlist.presentation.view.productslist.ProductListPresentationModule
import com.example.fakeshop.productlist.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.productlist.presentation.viewModel.FiltersPresentationModule
import com.example.registartion_login.login.data.LoginDataModule
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module


@Component(
    modules = [
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
        MainActivityModule::class,
        AppModule::class,
        RetrofitModule::class,
        LoginDataModule::class
    ]
)

interface AppComponent {
    fun inject(fragment: ProductsListFragment)
    fun inject(filtersFragment: FiltersFragment)
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@Module
interface AppModule {
    @Binds
    fun bindAppRouter(appRouterImpl: AppRouterImpl): AppRouter
}
