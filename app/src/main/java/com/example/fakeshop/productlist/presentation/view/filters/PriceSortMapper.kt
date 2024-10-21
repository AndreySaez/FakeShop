package com.example.fakeshop.productlist.presentation.view.filters

import com.example.fakeshop.productlist.domain.price.PriceSort
import javax.inject.Inject

class PriceSortMapper @Inject constructor() {
    fun priceToInput(priceSort: PriceSort?) = InputPriceSort(
        priceSort?.priceMin,
        priceSort?.priceMax
    )

    fun inputToPrice(inputPriceSort: InputPriceSort) = inputPriceSort.priceMin?.let { priceMin ->
        inputPriceSort.priceMax?.let { priceMax ->
            PriceSort(
                priceMin,
                priceMax
            )
        }
    }
}