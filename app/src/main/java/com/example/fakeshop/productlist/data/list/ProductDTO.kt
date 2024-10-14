package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.productlist.domain.category.Category
import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("price") val price: Int,
    @SerializedName("description") val description: String,
    @SerializedName("images") val images: List<String>,
    @SerializedName("category") val category: Category
)