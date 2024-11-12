package com.example.productslist.data.category

import com.example.coremodule.ApiInterface
import com.example.coremodule.productlist.Category
import com.example.productslist.domain.category.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: CategoryMapper
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> = withContext(Dispatchers.IO) {
        apiInterface.getCategories().map(mapper::toCategory)
    }
}