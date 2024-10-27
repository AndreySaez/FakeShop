package com.example.fakeshop.main_activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.coremodule.ViewModelFactory
import com.example.fakeshop.R
import com.example.fakeshop.appComponent
import com.example.productslist.presentation.view.productslist.ProductsListFragment
import com.example.registartion_login.login.presentation.view.LoginFragment
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainActivityViewModel> { viewmodelFactory }

    @Inject
    lateinit var viewmodelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent.inject(this)
        viewModel.getProfile()
        viewModel.mainEventFlow.onEach {
            when (it) {
                is OpenAppOneTimeEvent.NavigateToLoginFragment -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.main, LoginFragment())
                        .commit()
                }

                is OpenAppOneTimeEvent.NaviGateToProductListFragment -> {
                    supportFragmentManager.beginTransaction()
                        .add(R.id.main, ProductsListFragment())
                        .commit()
                }
            }
        }.launchIn(lifecycleScope)
    }
}