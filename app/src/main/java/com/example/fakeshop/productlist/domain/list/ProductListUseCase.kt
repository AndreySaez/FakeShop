package com.example.fakeshop.productlist.domain.list

import javax.inject.Inject

class ProductListUseCase @Inject constructor(
    private val repository: ProductListRepository
) {
    suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        category: Category? = null,
        sort: PriceSort = PriceSort.DEFAULT,
    ): ProductListResult {
        val request = repository.getProductList(nextPage, pageSize, category, sort)
        return ProductListResult(productList = request)
    }
}

data class ProductListResult(
    val productList: List<Product>
)