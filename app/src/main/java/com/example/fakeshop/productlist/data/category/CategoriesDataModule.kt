package com.example.fakeshop.productlist.data.category

import com.example.fakeshop.productlist.domain.category.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CategoriesDataModule {

    @Binds
    fun repository(impl: CategoryRepositoryImpl): CategoryRepository
}