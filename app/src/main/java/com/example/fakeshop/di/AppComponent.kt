package com.example.fakeshop.di

import android.content.Context
import com.example.applauncher.MainActivityModule
import com.example.coremodule.RetrofitModule
import com.example.productdetails.ProductDetailsModule
import com.example.productslist.data.category.CategoriesDataModule
import com.example.productslist.data.list.ProductListDataModule
import com.example.productslist.presentation.view.productslist.ProductListPresentationModule
import com.example.productslist.presentation.viewModel.FiltersPresentationModule
import com.example.registartion_login.login.data.LoginDataModule
import com.example.registartion_login.login.presentation.LoginPresentationModule
import com.example.registartion_login.registration.data.RegistrationDataModule
import com.example.registartion_login.registration.presentation.RegistrationPresentationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


@Component(
    modules = [
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
        MainActivityModule::class,
        LoginDataModule::class,
        ProductDetailsModule::class,
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        LoginPresentationModule::class,
        RetrofitModule::class
    ]
)
@Singleton

interface AppComponent : AppComponentInterface {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
