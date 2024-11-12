package com.example.productslist.presentation.view.productslist

import androidx.fragment.app.FragmentManager
import com.example.productslist.R
import com.example.productslistapi.ProductsListFragmentLauncher
import javax.inject.Inject

class ProductsListFragmentLauncherImpl @Inject constructor() : ProductsListFragmentLauncher {
    override fun replaceProductsListFragment(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(R.id.main, ProductsListFragment()).commit()
    }

    override fun addProductsListFragment(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .add(R.id.main, ProductsListFragment())
            .commit()
    }
}