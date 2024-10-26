package com.example.coremodule.productlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Category(
    val name: String,
    val id: Int,
) : Parcelable
