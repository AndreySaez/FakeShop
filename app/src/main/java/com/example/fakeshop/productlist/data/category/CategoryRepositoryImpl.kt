package com.example.fakeshop.productlist.data.category

import com.example.fakeshop.productlist.domain.category.CategoryRepository
import com.example.fakeshop.productlist.domain.list.Category
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor() : CategoryRepository {
    override suspend fun getCategories(): List<Category> = listOf(
        Category("Обувь", "footwear"),
        Category("Фурнитура", "furniture"),
        Category("Ювелирные изделия", "jewellery"),
        Category("Часы", "watches"),
        Category("Техника", "computers"),
    )
}