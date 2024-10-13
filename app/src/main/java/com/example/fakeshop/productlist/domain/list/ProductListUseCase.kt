package com.example.fakeshop.productlist.domain.list

import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.price.PriceSort
import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val repository: ProductListRepository
) {
    suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        priceSort: PriceSort? = null,
        category: Category? = null,

        ): ProductListResult {
        val request = repository.getProductList(
            nextPage = nextPage,
            pageSize = pageSize,
            priceSort = priceSort,
            category = category
        )
        return ProductListResult(productList = request)
    }
}

data class ProductListResult(
    val productList: List<Product>
)