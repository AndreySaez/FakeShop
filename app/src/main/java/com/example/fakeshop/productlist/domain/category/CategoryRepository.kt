package com.example.fakeshop.productlist.domain.category

import com.example.fakeshop.productlist.domain.list.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
