package com.example.registartion_login.login

import android.content.Context
import com.example.coremodule.RetrofitModule
import com.example.productslistapi.ProductsListDependenciesProvider
import com.example.registartion_login.login.data.LoginDataModule
import com.example.registartion_login.login.data.updateTokens.workManager.UpdateTokensWorkerImpl
import com.example.registartion_login.login.presentation.LoginPresentationModule
import com.example.registartion_login.login.presentation.view.LoginFragment
import com.example.registration_login_api.registration.RegistrationDependenciesProvider
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        RetrofitModule::class,
        LoginPresentationModule::class,
        LoginDataModule::class,
    ],
    dependencies = [ProductsListDependenciesProvider::class, RegistrationDependenciesProvider::class]
)
@Singleton
interface LoginComponent {
    fun inject(fragment: LoginFragment)
    fun inject(worker: UpdateTokensWorkerImpl)

    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance context: Context,
            productsListDependency: ProductsListDependenciesProvider,
            registrationDependency: RegistrationDependenciesProvider
        ): LoginComponent
    }
}