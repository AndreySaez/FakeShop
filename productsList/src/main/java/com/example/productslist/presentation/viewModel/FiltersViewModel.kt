package com.example.productslist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coremodule.productlist.Category
import com.example.productslist.R
import com.example.productslist.domain.category.CategoryRepository
import com.example.productslist.domain.price.PriceSort
import com.example.productslist.presentation.view.filters.InputPriceSort
import com.example.productslist.presentation.view.filters.PriceSortMapper
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val mapper: PriceSortMapper
) : ViewModel() {
    val state: StateFlow<FiltersState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow(FiltersState.INITIAL)

    val oneTimeEvents get() = _oneTimeEvents.asSharedFlow()
    private val _oneTimeEvents = MutableSharedFlow<FiltersOneTimeEvent>()

    init {
        viewModelScope.launch {
            val categories = categoryRepository.getCategories()
            _state.value = _state.value.copy(categories = categories)
        }
    }

    fun onAction(action: FiltersAction) = when (action) {
        is FiltersAction.OnCategoryClicked -> changeCategory(action.category)
        is FiltersAction.SetInitialFilters -> setInitialFilters(action.sort, action.category)
        is FiltersAction.OnMaximumPriceChanged -> changeMaximumPriceSort(action.price)
        is FiltersAction.OnMinimalPriceChanged -> changeMinimalPriceSort(action.price)
        FiltersAction.SubmitFilters -> submit()
    }

    private fun setInitialFilters(sort: PriceSort?, category: Category?) {
        val inputSort = mapper.priceToInput(sort)
        _state.value = state.value.copy(
            selectedCategory = category,
            priceSort = inputSort
        )
    }

    private fun changeMaximumPriceSort(priceValue: Int?) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort.copy(priceMax = priceValue)
        _state.value = _state.value.copy(priceSort = newPriceSort)
    }

    private fun changeMinimalPriceSort(priceValue: Int?) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort.copy(priceMin = priceValue)
        _state.value = _state.value.copy(priceSort = newPriceSort)
    }

    private fun changeCategory(category: Category) {
        val currentState = state.value
        _state.value = currentState.copy(
            selectedCategory = if (category == currentState.selectedCategory) null else category
        )
    }

    private fun submit() {
        viewModelScope.launch {
            val currentState = state.value
            if (handleError(currentState)) {
                _oneTimeEvents.emit(
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
            _oneTimeEvents.emit(
                FiltersOneTimeEvent.MakePriceSortErrorToast(R.string.max_price_must_be_higher_then_min_price_toast)
            )
            return false
        } catch (e: IllegalStateException) {
            _oneTimeEvents.emit(
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
