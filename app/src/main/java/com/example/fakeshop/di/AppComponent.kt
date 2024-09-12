package com.example.fakeshop.di

import androidx.lifecycle.ViewModel
import com.example.fakeshop.data.api.ApiInterface
import com.example.fakeshop.data.category.CategoriesDataModule
import com.example.fakeshop.data.login.LoginDataModule
import com.example.fakeshop.data.productslist.ProductListDataModule
import com.example.fakeshop.data.registration.RegistrationDataModule
import com.example.fakeshop.domain.login.LoginPresentationModule
import com.example.fakeshop.domain.productslist.ProductListPresentationModule
import com.example.fakeshop.domain.registration.RegistrationPresentationModule
import com.example.fakeshop.presentation.view.filters.FiltersFragment
import com.example.fakeshop.presentation.view.login.LoginFragment
import com.example.fakeshop.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.presentation.view.registration.RegistrationFragment
import com.example.fakeshop.presentation.viewModel.FiltersPresentationModule
import dagger.Component
import dagger.MapKey
import dagger.Module
import dagger.Provides
import kotlin.reflect.KClass

@Component(
    modules = [
        AppModule::class,
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        LoginPresentationModule::class,
        LoginDataModule::class,
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ProductsListFragment)
    fun inject(filtersFragment: FiltersFragment)
}

@Module
class AppModule {
    @Provides
    fun apiInterface() = ApiInterface.create()
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
