package com.example.fakeshop.productlist.presentation.viewModel

import com.example.fakeshop.productlist.domain.list.Category
import com.example.fakeshop.productlist.domain.list.PriceSort

sealed interface FiltersOneTimeEvent {
    data class SubmitResults(val category: Category?, val sort: PriceSort) : FiltersOneTimeEvent
}

sealed interface ProductsListEvents {
    data class OpenFilters(val category: Category?, val sort: PriceSort) : ProductsListEvents
}