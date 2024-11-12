package com.example.productslist.data.category

import com.example.productslist.domain.category.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CategoriesDataModule {

    @Binds
    fun repository(impl: CategoryRepositoryImpl): CategoryRepository
}