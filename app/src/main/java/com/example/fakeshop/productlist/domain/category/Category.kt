package com.example.fakeshop.productlist.domain.category

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val id: Int,
) : Parcelable
