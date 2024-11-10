package com.example.productslistapi

import androidx.fragment.app.FragmentManager

interface ProductsListFragmentLauncher {
    fun openProductsList(fragmentManager: FragmentManager)
}