package com.example.productslist.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.example.coremodule.BaseViewModel
import com.example.coremodule.productlist.Category
import com.example.coremodule.productlist.Product
import com.example.productslist.domain.list.ProductListUseCase
import com.example.productslist.presentation.view.filters.InputPriceSort
import com.example.productslist.presentation.view.filters.PriceSortMapper
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProductListViewModel @Inject constructor(
    private val getProductsUseCase: ProductListUseCase,
    private val mapper: PriceSortMapper
) : BaseViewModel<ProductAction, ProductsListState, ProductsListEvents>(ProductsListState.IsEmpty) {

    private var hasMoreItems = true
    private var loadingJob: Job? = null

    private var nextPage = INITIAL_PAGE
    private var currentCategory: Category? = null
    private var currentSort = InputPriceSort(null, null)

    init {
        loadInitial()
    }

    private fun loadInitial() {
        if (loadingJob?.isActive == true) loadingJob?.cancel()
        loadingJob = viewModelScope.launch {
            setState(ProductsListState.IsLoading)
            resetPagination()
            load()
        }
    }

    private fun resetPagination() {
        nextPage = INITIAL_PAGE
        hasMoreItems = true
    }

    private fun loadNext() {
        if (loadingJob?.isActive == true || !hasMoreItems) return
        loadingJob = viewModelScope.launch { load() }
    }

    private suspend fun load() {
        val loadedProducts = try {
            getProductsUseCase.getProductList(
                nextPage = nextPage,
                pageSize = PAGE_SIZE,
                priceSort = mapper.inputToPrice(currentSort),
                category = currentCategory
            ).productList
        } catch (e: Exception) {
            handleLoadingError()
            return
        }
        checkPaginationState(loadedProducts)
        val products = mergeLoadedItems(loadedProducts)
        showListOrEmpty(products)
    }

    private fun mergeLoadedItems(loadedProducts: List<Product>): List<Product> {
        val currentList = (state.value as? ProductsListState.ProductsList)?.products ?: emptyList()
        return currentList + loadedProducts
    }

    private fun handleLoadingError() {
        val currentState = state.value
        if (currentState is ProductsListState.ProductsList) {
            currentState.copy(hasLoadingError = true)
        } else {
            ProductsListState.IsError
        }.also {
            setState(it)
        }
    }

    private fun checkPaginationState(loadedProducts: List<Product>) {
        if (loadedProducts.size < PAGE_SIZE) {
            hasMoreItems = false
        } else {
            nextPage++
        }
    }

    private fun showListOrEmpty(products: List<Product>) {
        if (products.isNotEmpty()) {
            ProductsListState.ProductsList(products, hasMoreItems, hasLoadingError = false)
        } else {
            ProductsListState.IsEmpty
        }.also {
            setState(it)
        }
    }

    override fun onAction(action: ProductAction) {
        when (action) {
            ProductAction.OnScrollToEnd -> loadNext()
            ProductAction.Reload -> loadInitial()
            is ProductAction.ChangeFilters -> changeCategory(
                action.category,
                mapper.priceToInput(action.sort)
            )

            ProductAction.OnFiltersClick -> openFilters()
        }
    }

    private fun openFilters() = viewModelScope.launch {
        setEvent(ProductsListEvents.OpenFilters(currentCategory, currentSort))
    }

    private fun changeCategory(category: Category?, sort: InputPriceSort?) {
        if (currentCategory == category && currentSort == sort) return
        currentCategory = category
        if (sort != null) {
            currentSort = sort
        }
        loadInitial()
    }

    companion object {
        const val PAGE_SIZE = 20
        const val INITIAL_PAGE = 0
    }
}

sealed interface ProductsListState {
    data class ProductsList(
        val products: List<Product>,
        val hasMoreItems: Boolean = false,
        val hasLoadingError: Boolean = false,
    ) : ProductsListState

    data object IsEmpty : ProductsListState
    data object IsLoading : ProductsListState
    data object IsError : ProductsListState
}