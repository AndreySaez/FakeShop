package com.example.fakeshop.productlist.domain.category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
