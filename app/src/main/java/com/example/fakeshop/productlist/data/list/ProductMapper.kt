package com.example.fakeshop.productlist.data.list

import com.example.coremodule.productlist.ProductDTO
import com.example.fakeshop.productlist.domain.category.Category
import com.example.fakeshop.productlist.domain.list.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toProduct(productDTO: ProductDTO) = Product(
        id = productDTO.id,
        name = productDTO.title,
        category = Category(productDTO.title, productDTO.id.toInt()),
        price = productDTO.price,
        images = productDTO.images,
        description = productDTO.description
    )
}