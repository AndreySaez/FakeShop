package com.example.fakeshop.productlist.domain.category

import com.example.fakeshop.productlist.domain.productslist.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
