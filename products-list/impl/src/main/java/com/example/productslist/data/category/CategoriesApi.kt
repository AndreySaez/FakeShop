package com.example.productslist.data.category

import com.example.coremodule.productlist.CategoryResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoriesApi {
    @GET("categories")
    suspend fun getCategories(
        @Query("id") id: Int? = null
    ): List<CategoryResponse>
}