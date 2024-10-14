package com.example.fakeshop.productlist.data.category

import com.example.fakeshop.ApiInterface
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.category.CategoryRepository
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