package com.example.fakeshop

import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentManager
import com.example.coremodule.AppRouter
import com.example.coremodule.productlist.Product
import com.example.productdetails.presentation.ProductDetailsFragment
import com.example.productslist.presentation.view.productslist.ProductsListFragment
import com.example.registartion_login.R
import com.example.registartion_login.registration.presentation.view.RegistrationFragment
import javax.inject.Inject

class AppRouterImpl @Inject constructor() : AppRouter {
    override fun openRegistration(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction().addToBackStack(null)
            .add(R.id.main, RegistrationFragment()).commitAllowingStateLoss()
    }

    override fun openProductsList(fragmentManager: FragmentManager) {
        fragmentManager.beginTransaction()
            .replace(R.id.main, ProductsListFragment()).commit()
    }

    private val PRODUCT_KEY = "Product"
    override fun newInstance(product: Product): ProductDetailsFragment {
        return ProductDetailsFragment().apply {
            arguments = bundleOf(PRODUCT_KEY to product)
        }
    }
}