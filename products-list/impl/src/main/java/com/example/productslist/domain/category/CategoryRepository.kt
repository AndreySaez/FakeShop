package com.example.productslist.domain.category

import com.example.coremodule.productlist.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
