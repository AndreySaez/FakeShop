package com.example.productslist.domain.price

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PriceSort(
    val priceMin: Int,
    val priceMax: Int
) : Parcelable