package com.example.fakeshop.productlist.domain.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val id: String,
) : Parcelable
