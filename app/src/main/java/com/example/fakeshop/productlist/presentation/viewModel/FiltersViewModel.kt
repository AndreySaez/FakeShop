package com.example.fakeshop.productlist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.category.CategoryRepository
import com.example.fakeshop.productlist.domain.price.PriceSort
import com.example.fakeshop.productlist.presentation.view.filters.InputPriceSort
import com.example.fakeshop.productlist.presentation.view.filters.PriceSortMapper
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
    val priceSort: InputPriceSort
) {
    companion object {
        val INITIAL = FiltersState(priceSort = InputPriceSort(null, null))
    }
}
