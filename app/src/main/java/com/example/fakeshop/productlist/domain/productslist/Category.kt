package com.example.fakeshop.productlist.domain.productslist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val id: String,
) : Parcelable
