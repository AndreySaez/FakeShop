package com.example.productslist.presentation.viewModel

import androidx.lifecycle.viewModelScope
import com.example.coremodule.BaseViewModel
import com.example.coremodule.productlist.Category
import com.example.productslist.R
import com.example.productslist.domain.category.CategoryRepository
import com.example.productslist.domain.price.PriceSort
import com.example.productslist.presentation.view.filters.InputPriceSort
import com.example.productslist.presentation.view.filters.PriceSortMapper
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val mapper: PriceSortMapper
) : BaseViewModel<FiltersAction, FiltersState, FiltersOneTimeEvent>(FiltersState.INITIAL) {
    init {
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            val newState = state.value.copy(categories = categories)
            setState(newState)
        }
    }

    override fun onAction(action: FiltersAction) = when (action) {
        is FiltersAction.OnCategoryClicked -> changeCategory(action.category)
        is FiltersAction.SetInitialFilters -> setInitialFilters(action.sort, action.category)
        is FiltersAction.OnMaximumPriceChanged -> changeMaximumPriceSort(action.price)
        is FiltersAction.OnMinimalPriceChanged -> changeMinimalPriceSort(action.price)
        FiltersAction.SubmitFilters -> submit()
    }

    private fun setInitialFilters(sort: PriceSort?, category: Category?) {
        val inputSort = mapper.priceToInput(sort)
        val newState = state.value.copy(
            selectedCategory = category,
            priceSort = inputSort
        )
        setState(newState)
    }

    private fun changeMaximumPriceSort(priceValue: Int?) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort.copy(priceMax = priceValue)
        val newState = state.value.copy(priceSort = newPriceSort)
        setState(newState)
    }

    private fun changeMinimalPriceSort(priceValue: Int?) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort.copy(priceMin = priceValue)
        val newState = state.value.copy(priceSort = newPriceSort)
        setState(newState)
    }

    private fun changeCategory(category: Category) {
        val currentState = state.value
        val newState = currentState.copy(
            selectedCategory = if (category == currentState.selectedCategory) null else category
        )
        setState(newState)
    }

    private fun submit() {
        viewModelScope.launch {
            val currentState = state.value
            if (handleError(currentState)) {
                setEvent(
                    FiltersOneTimeEvent.SubmitResults(
                        currentState.selectedCategory, currentState.priceSort
                    )
                )
            }
        }
    }

    private suspend fun handleError(currentState: FiltersState): Boolean {
        try {
            val currentMinPrice = currentState.priceSort.priceMin
            val currentMaxPrice = currentState.priceSort.priceMax
            if (currentMinPrice != null && currentMaxPrice == null) throw IllegalStateException()
            if (currentMinPrice == null && currentMaxPrice != null) throw IllegalStateException()
            if (currentMinPrice != null && currentMaxPrice != null) {
                if (currentMinPrice > currentMaxPrice) throw IllegalArgumentException()
            }
        } catch (e: IllegalArgumentException) {
            setEvent(
                FiltersOneTimeEvent.MakePriceSortErrorToast(R.string.max_price_must_be_higher_then_min_price_toast)
            )
            return false
        } catch (e: IllegalStateException) {
            setEvent(
                FiltersOneTimeEvent.MakePriceSortErrorToast(R.string.both_prices_must_be_not_empty)
            )
            return false
        }
        return true
    }
}

data class FiltersState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val priceSort: InputPriceSort
) {
    companion object {
        val INITIAL = FiltersState(priceSort = InputPriceSort(null, null))
    }
}
