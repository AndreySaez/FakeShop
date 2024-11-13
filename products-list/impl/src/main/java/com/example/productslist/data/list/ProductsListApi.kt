package com.example.productslist.data.list

import com.example.coremodule.productlist.ProductDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsListApi {
    @GET("products")
    suspend fun getProductList(
        @Query("price_min") priceMin: Int? = null,
        @Query("price_max") priceMax: Int? = null,
        @Query("limit") limit: Int = 20,
        @Query("offset") page: Int = 0,
        @Query("categoryId") categoryId: Int? = null
    ): List<ProductDTO>
}