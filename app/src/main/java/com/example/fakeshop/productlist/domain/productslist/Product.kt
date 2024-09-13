package com.example.fakeshop.productlist.domain.productslist

data class Product(
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Int,
    val discountPrice: Int,
    val images: List<String>,
    val description: String
)