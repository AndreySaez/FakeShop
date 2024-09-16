package com.example.fakeshop.productlist.domain.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val category: List<String>,
    val price: Int,
    val discountPrice: Int,
    val images: List<String>,
    val description: String
) : Parcelable