package com.example.fakeshop.data.productslist

import com.google.gson.annotations.SerializedName

data class ProductDTO(
    @SerializedName("_id") val id: String,
    @SerializedName("name") val name: String,
    @SerializedName("category") val category: List<String>?,
    @SerializedName("price") val price: Int,
    @SerializedName("discounted_price") val discountPrice: Int?,
    @SerializedName("images") val images: List<String>?,
    @SerializedName("description") val description: String?
)