package com.example.fakeshop

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.fakeshop.login.presentation.view.LoginFragment
import com.example.fakeshop.login.presentation.viewmodel.LoginViewModel
import com.example.fakeshop.productlist.presentation.view.productslist.ProductsListFragment
import com.example.fakeshop.productlist.presentation.viewModel.ViewModelFactory
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val loginViewModel by viewModels<LoginViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        loginViewModel.getProfile()
        loginViewModel.mainEventFlow.onEach {
            when (it) {
                is OpenAppOneTimeEvent.NavigateToLoginFragment -> {
                    supportFragmentManager.beginTransaction().add(R.id.main, LoginFragment())
                        .commit()
                }

                OpenAppOneTimeEvent.NaviGateToProductListFragment -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.main, ProductsListFragment())
                        .commit()
                }
            }
        }.launchIn(lifecycleScope)
    }
}