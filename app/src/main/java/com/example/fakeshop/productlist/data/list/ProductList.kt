package com.example.fakeshop.productlist.data.list

import com.google.gson.annotations.SerializedName

data class ProductList(
    @SerializedName("Data") val data: List<ProductDTO>,
    @SerializedName("status") val status: String
)