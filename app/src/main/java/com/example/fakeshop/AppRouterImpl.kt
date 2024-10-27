package com.example.fakeshop

import androidx.fragment.app.FragmentManager
import com.example.coremodule.AppRouter
import com.example.productslist.presentation.view.productslist.ProductsListFragment
import com.example.registartion_login.R
import javax.inject.Inject

class AppRouterImpl @Inject constructor() : AppRouter {
    override fun openProductsList(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(R.id.main, ProductsListFragment()).commit()
    }
}