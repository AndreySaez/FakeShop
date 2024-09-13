package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.ApiInterface
import com.example.fakeshop.productlist.domain.productslist.Category
import com.example.fakeshop.productlist.domain.productslist.PriceSort
import com.example.fakeshop.productlist.domain.productslist.Product
import com.example.fakeshop.productlist.domain.productslist.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProductListRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface,
    private val mapper: ProductMapper,
    private val sortMapper: SortMapper
) : ProductListRepository {
    override suspend fun getProductList(
        nextPage: Int,
        pageSize: Int,
        category: Category?,
        sort: PriceSort
    ): List<Product> = withContext(Dispatchers.IO) {
        apiInterface.productList(
            limit = pageSize.toString(),
            page = nextPage.toString(),
            category = category?.id,
            sort = sortMapper(sort)
        ).data.map(mapper::toProduct)
    }
}