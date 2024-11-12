package com.example.applauncher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coremodule.ViewModelFactory
import com.example.coremodule.findDependency
import com.example.productslistapi.ProductsListFragmentLauncher
import com.example.registration_login_api.login.LoginFragmentLauncher
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    @Inject
    lateinit var loginFragmentLauncher: LoginFragmentLauncher

    @Inject
    lateinit var productsListFragmentLauncher: ProductsListFragmentLauncher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DaggerMainActivityComponent.factory().create(
            context = applicationContext,
            productsList = applicationContext.findDependency(),
            login = applicationContext.findDependency(),
            profile = applicationContext.findDependency()
        ).inject(this)
        viewModel.getProfile()
        viewModel.mainEventFlow.onEach {
            when (it) {
                is OpenAppOneTimeEvent.NavigateToLoginFragment -> {
                    loginFragmentLauncher.addLoginFragment(supportFragmentManager)
                }

                is OpenAppOneTimeEvent.NaviGateToProductListFragment -> {
                    productsListFragmentLauncher.addProductsListFragment(supportFragmentManager)
                }
            }
        }.launchIn(lifecycleScope)
    }
}