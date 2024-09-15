package com.example.fakeshop.productlist.domain.list

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
enum class PriceSort : Parcelable {
    ASC, DESC, DEFAULT
}