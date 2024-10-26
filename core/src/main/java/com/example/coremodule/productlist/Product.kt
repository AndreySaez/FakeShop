package com.example.coremodule.productlist

import android.os.Parcelable
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