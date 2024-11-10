package com.example.fakeshop.di

import android.content.Context
import com.example.coremodule.RetrofitModule
import com.example.fakeshop.main_activity.MainActivity
import com.example.fakeshop.main_activity.MainActivityModule
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


@Component(
    modules = [
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
        MainActivityModule::class,
        RetrofitModule::class,
        LoginDataModule::class,
        ProductDetailsModule::class,
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        LoginDataModule::class,
        LoginPresentationModule::class
    ]
)

interface AppComponent : AppComponentInterface {
    fun inject(mainActivity: MainActivity)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}
