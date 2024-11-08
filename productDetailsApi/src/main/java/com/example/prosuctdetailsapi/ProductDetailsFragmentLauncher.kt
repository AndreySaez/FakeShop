package com.example.prosuctdetailsapi

import androidx.fragment.app.Fragment
import com.example.coremodule.productlist.Product

interface ProductDetailsFragmentLauncher {
    fun launchFragment(product: Product):Fragment
}