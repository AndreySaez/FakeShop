package com.example.fakeshop.productlist.domain.list

import android.os.Parcelable
import com.example.fakeshop.productlist.domain.category.Category
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: String,
    val name: String,
    val category: Category,
    val price: Int,
    val images: List<String>,
    val description: String
) : Parcelable