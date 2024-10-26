package com.example.fakeshop.productlist.presentation.viewModel

import com.example.coremodule.productlist.Category
import com.example.fakeshop.productlist.presentation.view.filters.InputPriceSort


sealed interface FiltersOneTimeEvent {
    data class SubmitResults(val category: Category?, val sort: InputPriceSort?) : FiltersOneTimeEvent
    data class MakePriceSortErrorToast(val text:Int):FiltersOneTimeEvent
}

sealed interface ProductsListEvents {
    data class OpenFilters(val category: Category?, val sort: InputPriceSort) : ProductsListEvents
}