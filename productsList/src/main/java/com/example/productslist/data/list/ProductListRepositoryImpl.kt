package com.example.productslist.data.list

import com.example.coremodule.ApiInterface
import com.example.coremodule.productlist.Category
import com.example.coremodule.productlist.Product
import com.example.productslist.domain.list.ProductListRepository
import com.example.productslist.domain.price.PriceSort
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: ProductMapper,
) : ProductListRepository {
    override suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        priceSort: PriceSort?,
        category: Category?
    ): List<Product> = withContext(Dispatchers.IO) {
        apiInterface.getProductList(
            limit = pageSize,
            page = nextPage,
            priceMin = priceSort?.priceMin,
            priceMax = priceSort?.priceMax,
            categoryId = category?.id
        ).map(mapper::toProduct)
    }
}