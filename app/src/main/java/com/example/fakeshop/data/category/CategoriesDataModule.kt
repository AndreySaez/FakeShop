package com.example.fakeshop.data.category

import com.example.fakeshop.domain.category.CategoryRepository
import dagger.Binds
import dagger.Module

@Module
interface CategoriesDataModule {

    @Binds
    fun repository(impl: CategoryRepositoryImpl): CategoryRepository
}