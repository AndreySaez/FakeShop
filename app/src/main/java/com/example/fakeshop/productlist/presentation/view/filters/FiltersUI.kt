package com.example.fakeshop.productlist.presentation.view.filters

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakeshop.R
import com.example.fakeshop.productlist.domain.productslist.Category
import com.example.fakeshop.productlist.domain.productslist.PriceSort
import com.example.fakeshop.productlist.presentation.viewModel.FiltersAction
import com.example.fakeshop.productlist.presentation.viewModel.FiltersState
import com.example.fakeshop.productlist.presentation.viewModel.FiltersViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun FiltersPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        FiltersScreen(
            FiltersState(
                categories = listOf(
                    Category("Категория", "dsad"),
                    Category("Категория", "dsad"),
                    Category("Категория", "dsad"),
                    Category("Категория", "dsad"),
                ),
                selectedCategory = Category("Ебанина", "dsad"),
                sorts = PriceSort.entries,
                selectedSort = PriceSort.DEFAULT
            )
        )
    }
}

@Composable
fun Filters(
    filtersViewModel: FiltersViewModel
) {
    val state by filtersViewModel.state.collectAsState()

    FiltersScreen(state = state, onAction = filtersViewModel::onAction)
}

@Composable
private fun FiltersScreen(
    state: FiltersState,
    onAction: (FiltersAction) -> Unit = {}
) {
    Box {
        Column(
            modifier = Modifier
                .background(
                    colorResource(id = R.color.background_color)
                )
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            Categories(
                categories = state.categories,
                selectedCategory = state.selectedCategory,
                onAction
            )
            Spacer(modifier = Modifier.height(10.dp))
            Sortings(sortings = state.sorts, currentSort = state.selectedSort, onAction)
            Spacer(Modifier.padding(16.dp))
            Button(
                onClick = { onAction(FiltersAction.SubmitFilters) },
                colors = ButtonDefaults.buttonColors().copy(containerColor = Color.White),
                shape = RoundedCornerShape(20),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, bottom = 8.dp, end = 8.dp)
            ) {
                Text(text = stringResource(id = R.string.submit_filters), color = Color.Black)
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ColumnScope.Categories(
    categories: List<Category>,
    selectedCategory: Category?,
    onAction: (FiltersAction) -> Unit
) {
    if (categories.isEmpty()) return

    Text(
        stringResource(id = R.string.categories_title),
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 10.dp)
    )

    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        categories.forEach { category ->
            val isCategorySelected = category == selectedCategory
            Text(
                category.name,
                color = Color.White,
                fontSize = 14.sp,
                modifier = Modifier
                    .background(
                        color = if (isCategorySelected) Color.Gray else Color.DarkGray,
                        shape = RoundedCornerShape(percent = 15)
                    )
                    .padding(vertical = 3.dp, horizontal = 5.dp)
                    .clickable {
                        onAction(FiltersAction.OnCategoryClicked(category))
                    }
            )
        }
    }

}

@Composable
fun ColumnScope.Sortings(
    sortings: List<PriceSort>,
    currentSort: PriceSort?,
    onAction: (FiltersAction) -> Unit
) {

    if (sortings.isEmpty()) return

    Text(
        stringResource(id = R.string.sortings_title),
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 10.dp)
    )

    sortings.forEach {
        SortingView(it, it == currentSort, onAction)
    }

}

@Composable
private fun SortingView(sort: PriceSort, isSelected: Boolean, onAction: (FiltersAction) -> Unit) {
    Row(
        Modifier.clickable {
            onAction(FiltersAction.OnSortingClicked(sort))
        }
    ) {
        Checkbox(
            checked = isSelected,
            onCheckedChange = {
            },
            colors = CheckboxDefaults.colors().copy(
                checkedBoxColor = Color.Gray
            )
        )
        Text(
            stringResource(id = sort.toName()),
            fontWeight = FontWeight.Bold,
            color = Color.White,
            fontSize = 20.sp,
            modifier = Modifier.padding(top = 10.dp)
        )
    }
}

private fun PriceSort.toName() = when (this) {
    PriceSort.DEFAULT -> R.string.sorting_default
    PriceSort.ASC -> R.string.sorting_price_ascending
    PriceSort.DESC -> R.string.sorting_price_descending
}