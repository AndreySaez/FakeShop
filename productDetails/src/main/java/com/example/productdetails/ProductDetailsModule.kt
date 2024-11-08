package com.example.productdetails

import com.example.productdetails.presentation.ProductDetailsFragmentLauncherImpl
import com.example.prosuctdetailsapi.ProductDetailsFragmentLauncher
import dagger.Binds
import dagger.Module

@Module
interface ProductDetailsModule {
    @Binds
    fun bindProductDetailsFragmentLauncher(impl: ProductDetailsFragmentLauncherImpl): ProductDetailsFragmentLauncher
}