package com.example.fakeshop.productlist.domain.productslist

interface ProductListRepository {
    suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        category: Category?,
        sort: PriceSort
    ): List<Product>
}