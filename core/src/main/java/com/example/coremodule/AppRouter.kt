package com.example.coremodule

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.coremodule.productlist.Product

interface AppRouter {
    fun openRegistration(fragmentManager: FragmentManager)
    fun openProductsList(fragmentManager: FragmentManager)
    fun newInstance(product: Product): Fragment
}