package com.example.registartion_login.login

import android.content.Context
import com.example.coremodule.AppRouter
import com.example.coremodule.RetrofitModule
import com.example.productslistapi.ProductsListDependenciesProvider
import com.example.registartion_login.login.data.LoginDataModule
import com.example.registartion_login.login.data.updateTokens.workManager.UpdateTokensWorkerImpl
import com.example.registartion_login.login.presentation.LoginPresentationModule
import com.example.registartion_login.login.presentation.view.LoginFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    modules = [
        LoginPresentationModule::class,
        LoginDataModule::class,
        RetrofitModule::class
    ], dependencies = [AppRouter::class, ProductsListDependenciesProvider::class]
)
interface LoginComponent {
    fun inject(fragment: LoginFragment)
    fun inject(worker: UpdateTokensWorkerImpl)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            appRouter: AppRouter,
            dependencies: ProductsListDependenciesProvider
        ): LoginComponent
    }
}