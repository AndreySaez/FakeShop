package com.example.fakeshop.productlist.data.category

import com.example.coremodule.productlist.CategoryResponse
import com.example.coremodule.productlist.Category
import javax.inject.Inject

class CategoryMapper @Inject constructor() {
    fun toCategory(categoryResponse: CategoryResponse) = Category(
        name = categoryResponse.name,
        id = categoryResponse.id
    )
}