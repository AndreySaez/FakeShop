package com.example.productslist.data.category

import com.example.coremodule.productlist.Category
import com.example.productslist.domain.category.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoriesApi: CategoriesApi,
    private val mapper: CategoryMapper
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        categoriesApi.getCategories().map(mapper::toCategory)
    }
}