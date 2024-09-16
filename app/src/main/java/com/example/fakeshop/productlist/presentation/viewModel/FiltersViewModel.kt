package com.example.fakeshop.productlist.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fakeshop.productlist.domain.category.CategoryRepository
import com.example.fakeshop.productlist.domain.list.Category
import com.example.fakeshop.productlist.domain.list.PriceSort
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
                categories = categoryRepository.getCategories(),
                sorts = PriceSort.entries
            )
        }
    }

    fun onAction(action: FiltersAction) = when (action) {
        is FiltersAction.OnCategoryClicked -> changeCategory(action.category)
        is FiltersAction.OnSortingClicked -> changeSorting(action.sort)
        is FiltersAction.SetInitialFilters -> setInitialFilters(action.sort, action.category)
        FiltersAction.SubmitFilters -> submit()
    }

    private fun setInitialFilters(sort: PriceSort, category: Category?) {
        _state.value = state.value.copy(
            selectedCategory = category,
            selectedSort = sort
        )
    }

    private fun changeSorting(sort: PriceSort) {
        val currentState = state.value
        _state.value = currentState.copy(
            selectedSort = sort
        )
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
                    currentState.selectedCategory, currentState.selectedSort
                )
            )
        }
    }
}

data class FiltersState(
    val categories: List<Category> = emptyList(),
    val selectedCategory: Category? = null,
    val sorts: List<PriceSort> = emptyList(),
    val selectedSort: PriceSort = PriceSort.DEFAULT
) {
    companion object {
        val INITIAL = FiltersState()
    }
}
