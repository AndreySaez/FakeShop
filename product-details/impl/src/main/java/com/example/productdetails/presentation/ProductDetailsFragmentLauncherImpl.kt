package com.example.productdetails.presentation

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.coremodule.productlist.Product
import com.example.prosuctdetailsapi.ProductDetailsFragmentLauncher
import javax.inject.Inject

class ProductDetailsFragmentLauncherImpl @Inject constructor() : ProductDetailsFragmentLauncher {

    private val PRODUCT_KEY = "Product"
    override fun launchProductDetailsFragment(product: Product): Fragment {
        return ProductDetailsFragment().apply {
            arguments = bundleOf(PRODUCT_KEY to product)
        }
    }
}