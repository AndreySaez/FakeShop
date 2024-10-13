package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.ApiInterface
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.list.Product
import com.example.fakeshop.productlist.domain.list.ProductListRepository
import com.example.fakeshop.productlist.domain.price.PriceSort
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