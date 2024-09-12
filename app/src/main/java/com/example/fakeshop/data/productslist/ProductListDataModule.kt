package com.example.fakeshop.data.productslist

import com.example.fakeshop.domain.productslist.ProductListRepository
import dagger.Binds
import dagger.Module

@Module(includes = [ProductListDataModule.Declarations::class])
interface ProductListDataModule {
    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(productListRepositoryImpl: ProductListRepositoryImpl): ProductListRepository
    }
}