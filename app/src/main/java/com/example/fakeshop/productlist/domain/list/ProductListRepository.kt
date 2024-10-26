package com.example.fakeshop.productlist.domain.list

import com.example.coremodule.productlist.Product
import com.example.coremodule.productlist.Category
import com.example.fakeshop.productlist.domain.price.PriceSort


interface ProductListRepository {
    suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        priceSort: PriceSort? = null,
        category: Category? = null
    ): List<Product>
}