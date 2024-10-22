package com.example.fakeshop.di

import android.content.Context
import androidx.lifecycle.ViewModel
import com.example.coremodule.RetrofitModule
import com.example.fakeshop.login.data.LoginDataModule
import com.example.fakeshop.login.data.updateTokens.workManager.UpdateTokensWorkerImpl
import com.example.fakeshop.login.presentation.LoginPresentationModule
import com.example.fakeshop.login.presentation.view.LoginFragment
import com.example.fakeshop.main_activity.MainActivity
import com.example.fakeshop.main_activity.MainActivityModule
import com.example.fakeshop.productlist.data.category.CategoriesDataModule
import com.example.fakeshop.productlist.data.list.ProductListDataModule
import com.example.fakeshop.productlist.domain.list.ProductListPresentationModule
import com.example.fakeshop.productlist.presentation.view.filters.FiltersFragment
import com.example.fakeshop.productlist.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.productlist.presentation.viewModel.FiltersPresentationModule
import com.example.fakeshop.registration.data.RegistrationDataModule
import com.example.fakeshop.registration.presentation.RegistrationPresentationModule
import com.example.fakeshop.registration.presentation.view.RegistrationFragment
import dagger.BindsInstance
import dagger.Component
import dagger.MapKey
import kotlin.reflect.KClass


@Component(
    modules = [
        RegistrationPresentationModule::class,
        RegistrationDataModule::class,
        LoginPresentationModule::class,
        LoginDataModule::class,
        ProductListDataModule::class,
        ProductListPresentationModule::class,
        CategoriesDataModule::class,
        FiltersPresentationModule::class,
        MainActivityModule::class,
        RetrofitModule::class
    ]
)
interface AppComponent {
    fun inject(fragment: RegistrationFragment)
    fun inject(fragment: LoginFragment)
    fun inject(fragment: ProductsListFragment)
    fun inject(filtersFragment: FiltersFragment)
    fun inject(mainActivity: MainActivity)
    fun inject(worker: UpdateTokensWorkerImpl)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
annotation class ViewModelKey(val value: KClass<out ViewModel>)
