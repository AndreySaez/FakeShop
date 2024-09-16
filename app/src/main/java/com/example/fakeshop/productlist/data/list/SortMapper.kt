package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.productlist.domain.productslist.PriceSort
import javax.inject.Inject

class SortMapper @Inject constructor() {
    operator fun invoke(sort: PriceSort): String? = when (sort) {
        PriceSort.ASC -> "+price"
        PriceSort.DESC -> "-price"
        PriceSort.DEFAULT -> null
    }
}
