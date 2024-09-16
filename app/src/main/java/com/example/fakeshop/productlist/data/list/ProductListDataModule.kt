package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.productlist.domain.list.ProductListRepository
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