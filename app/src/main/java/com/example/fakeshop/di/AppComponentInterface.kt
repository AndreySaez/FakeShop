package com.example.fakeshop.di

import com.example.productslistapi.ProductsListDependenciesProvider
import com.example.prosuctdetailsapi.ProductDetailsDependenciesProvider

interface AppComponentInterface : ProductDetailsDependenciesProvider,
    ProductsListDependenciesProvider