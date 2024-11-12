package com.example.productslistapi

import androidx.fragment.app.FragmentManager

interface ProductsListFragmentLauncher {
    fun replaceProductsListFragment(fragmentManager: FragmentManager)
    fun addProductsListFragment(fragmentManager: FragmentManager)
}