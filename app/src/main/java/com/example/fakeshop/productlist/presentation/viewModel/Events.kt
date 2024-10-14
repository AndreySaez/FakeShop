package com.example.fakeshop.productlist.presentation.viewModel

import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.presentation.view.filters.InputPriceSort


sealed interface FiltersOneTimeEvent {
    data class SubmitResults(val category: Category?, val sort: InputPriceSort?) : FiltersOneTimeEvent
}

sealed interface ProductsListEvents {
    data class OpenFilters(val category: Category?, val sort: InputPriceSort) : ProductsListEvents
}