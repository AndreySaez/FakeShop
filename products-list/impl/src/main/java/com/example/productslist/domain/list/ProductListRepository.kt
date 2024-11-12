package com.example.productslist.domain.list

import com.example.coremodule.productlist.Product
import com.example.coremodule.productlist.Category
import com.example.productslist.domain.price.PriceSort


interface ProductListRepository {
    suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        priceSort: PriceSort? = null,
        category: Category? = null
    ): List<Product>
}