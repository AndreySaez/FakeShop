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
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fakeshop.R
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.price.PriceSort
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
                    Category("Категория", 1),
                    Category("Категория", 2),
                    Category("Категория", 3),
                    Category("Категория", 4),

                    ),
                selectedCategory = Category("Выбранная категория", 1),
                priceSort = PriceSort(null, null)
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
            Sorting(
                onAction
            )
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
fun ColumnScope.Sorting(
    onAction: (FiltersAction) -> Unit = {},
) {
    Text(
        stringResource(id = R.string.sortings_title),
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 20.sp,
        modifier = Modifier.padding(top = 10.dp)
    )
    Spacer(modifier = Modifier.padding(8.dp))
    SortingView(onAction)
}

@Composable
private fun SortingView(
    onAction: (FiltersAction) -> Unit = {},
    filterState: FiltersState = FiltersState.INITIAL
) {
    Column {
        PriceInputTextField(
            title = stringResource(id = R.string.minimal_price),
            value = if (filterState.priceSort?.priceMin == null) ""
            else filterState.priceSort.priceMin.toString(),
            onTextChanged = {
                if (it.isNotEmpty()) {
                    onAction(FiltersAction.OnMinimalPriceChanged(it.toInt()))
                }
            }
        )
        Spacer(modifier = Modifier.padding(4.dp))
        PriceInputTextField(
            value = if (filterState.priceSort?.priceMax == null) ""
            else filterState.priceSort.priceMax.toString(),
            title = stringResource(id = R.string.maximum_price),
            onTextChanged = {
                if (it.isNotEmpty()) {
                    onAction(FiltersAction.OnMaximumPriceChanged(it.toInt()))
                }
            }
        )
    }
}

@Composable
private fun PriceInputTextField(
    title: String = "",
    value: String,
    onTextChanged: (String) -> Unit = {},
) {
    var price by rememberSaveable { mutableStateOf(value) }
    OutlinedTextField(
        modifier = Modifier
            .height(55.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0xff262624),
            unfocusedContainerColor = Color(0xff262624),
            disabledContainerColor = Color(0xff262624),
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.LightGray,
            errorIndicatorColor = Color.Red,
            disabledTextColor = Color.White,
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            errorTextColor = Color.White
        ),
        singleLine = true,
        shape = Shapes().small,
        value = price,
        onValueChange = {
            price = it
            onTextChanged(price)
        },
        placeholder = {
            Text(text = title, color = Color(0xff9D9D9D))
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}