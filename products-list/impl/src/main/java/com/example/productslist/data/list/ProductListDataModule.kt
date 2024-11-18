package com.example.productslist.data.list

import com.example.productslist.domain.list.ProductListRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [ProductListDataModule.Declarations::class])
class ProductListDataModule {
    @Provides
    @Singleton
    fun productsApi(retrofit: Retrofit): ProductsListApi {
        return retrofit.create(ProductsListApi::class.java)
    }

    @Module
    abstract class Declarations {
        @Binds
        abstract fun bindRepository(productListRepositoryImpl: ProductListRepositoryImpl): ProductListRepository
    }
}