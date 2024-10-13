package com.example.fakeshop.productlist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.category.CategoryRepository
import com.example.fakeshop.productlist.domain.price.PriceSort
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FiltersViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
) : ViewModel() {
    val state: StateFlow<FiltersState> get() = _state.asStateFlow()
    private val _state = MutableStateFlow(FiltersState.INITIAL)

    val oneTimeEvents get() = _oneTimeEvents.asSharedFlow()
    private val _oneTimeEvents = MutableSharedFlow<FiltersOneTimeEvent>()

    init {
        viewModelScope.launch {
            val currentState = state.value
            _state.value = currentState.copy(
                categories = categoryRepository.getCategories()
            )
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
        _state.value = state.value.copy(
            selectedCategory = category,
            priceSort = sort
        )
    }

    private fun changeMaximumPriceSort(maxPrice: Int) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort?.copy(priceMax = maxPrice)
        _state.value = currentState.copy(priceSort = newPriceSort)
    }

    private fun changeMinimalPriceSort(minPrice: Int) {
        val currentState = state.value
        val newPriceSort = currentState.priceSort?.copy(priceMin = minPrice)
        _state.value = currentState.copy(priceSort = newPriceSort)
    }

    private fun changeCategory(category: Category) {
        val currentState = state.value
        _state.value = currentState.copy(
            selectedCategory = if (category == currentState.selectedCategory) null else category
        )
    }

    private fun submit() {
        val currentState = state.value
        viewModelScope.launch {
            _oneTimeEvents.emit(
                FiltersOneTimeEvent.SubmitResults(
                    currentState.selectedCategory, currentState.priceSort
                )
            )
        }
    }
}

data class FiltersState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val priceSort: PriceSort?
) {
    companion object {
        val INITIAL = FiltersState(priceSort = PriceSort(null, null))
    }
}
