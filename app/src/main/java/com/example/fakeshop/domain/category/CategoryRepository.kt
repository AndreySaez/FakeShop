package com.example.fakeshop.domain.category

import com.example.fakeshop.domain.productslist.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
