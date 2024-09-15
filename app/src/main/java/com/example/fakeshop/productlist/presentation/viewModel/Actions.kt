package com.example.fakeshop.productlist.presentation.viewModel

import com.example.fakeshop.productlist.domain.list.Category
import com.example.fakeshop.productlist.domain.list.PriceSort

sealed interface FiltersAction {
    data object SubmitFilters : FiltersAction
    data class OnCategoryClicked(val category: Category) : FiltersAction
    data class OnSortingClicked(val sort: PriceSort) : FiltersAction
    data class SetInitialFilters(val category: Category?, val sort: PriceSort) : FiltersAction
}

sealed interface ProductAction {
    data object OnScrollToEnd : ProductAction
    data object Reload : ProductAction
    data object OnFiltersClick : ProductAction
    data class ChangeFilters(
        val category: Category? = null,
        val sort: PriceSort = PriceSort.DEFAULT
    ) : ProductAction
}