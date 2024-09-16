package com.example.fakeshop.di

import androidx.lifecycle.ViewModel
import com.example.fakeshop.ApiInterface
import com.example.fakeshop.productlist.data.category.CategoriesDataModule
import com.example.fakeshop.login.data.LoginDataModule
import com.example.fakeshop.productlist.data.list.ProductListDataModule
import com.example.fakeshop.registration.data.RegistrationDataModule
import com.example.fakeshop.login.presentation.LoginPresentationModule
import com.example.fakeshop.productlist.domain.productslist.ProductListPresentationModule
import com.example.fakeshop.registration.presentation.RegistrationPresentationModule
import com.example.fakeshop.productlist.presentation.view.filters.FiltersFragment
import com.example.fakeshop.login.presentation.view.LoginFragment
import com.example.fakeshop.productlist.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.registration.presentation.view.RegistrationFragment
import com.example.fakeshop.productlist.presentation.viewModel.FiltersPresentationModule
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
