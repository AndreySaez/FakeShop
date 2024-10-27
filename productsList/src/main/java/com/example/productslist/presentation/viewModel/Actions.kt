package com.example.productslist.presentation.viewModel

import com.example.coremodule.productlist.Category
import com.example.productslist.domain.price.PriceSort

sealed interface FiltersAction {
    data object SubmitFilters : FiltersAction
    data class OnCategoryClicked(val category: Category) : FiltersAction
    data class OnMinimalPriceChanged(val price: Int?) : FiltersAction
    data class OnMaximumPriceChanged(val price: Int?) : FiltersAction
    data class SetInitialFilters(val category: Category?, val sort: PriceSort?) : FiltersAction
}

sealed interface ProductAction {
    data object OnScrollToEnd : ProductAction
    data object Reload : ProductAction
    data object OnFiltersClick : ProductAction
    data class ChangeFilters(
        val category: Category? = null,
        val sort: PriceSort? = null
    ) : ProductAction
}