package com.example.productslistapi

interface ProductsListDependenciesProvider {
    fun listFragmentLauncher(): ProductsListFragmentLauncher
}