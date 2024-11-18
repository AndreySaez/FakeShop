package com.example.productslist.data.category

import com.example.productslist.domain.category.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [CategoriesDataModule.Declarations::class])
class CategoriesDataModule {
    @Provides
    @Singleton
    fun categoriesApi(retrofit: Retrofit): CategoriesApi {
        return retrofit.create(CategoriesApi::class.java)
    }

    @Module
    abstract class Declarations {
        @Binds
        abstract fun repository(impl: CategoryRepositoryImpl): CategoryRepository
    }
}