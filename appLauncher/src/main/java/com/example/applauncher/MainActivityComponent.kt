package com.example.applauncher

import android.content.Context
import com.example.coremodule.RetrofitModule
import com.example.productslistapi.ProductsListDependenciesProvider
import com.example.registration_login_api.login.LoginDependenciesProvider
import com.example.registration_login_api.profile.ProfileDependenciesProvider
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        RetrofitModule::class,
        MainActivityModule::class,
    ], dependencies = [
        ProductsListDependenciesProvider::class,
        LoginDependenciesProvider::class,
        ProfileDependenciesProvider::class
    ]
)
interface MainActivityComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            productsList: ProductsListDependenciesProvider,
            login: LoginDependenciesProvider,
            profile: ProfileDependenciesProvider
        ): MainActivityComponent
    }

    fun inject(mainActivity: MainActivity)
}