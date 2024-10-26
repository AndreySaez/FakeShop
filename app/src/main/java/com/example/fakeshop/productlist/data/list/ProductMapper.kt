package com.example.fakeshop.productlist.data.list

import com.example.coremodule.productlist.ProductDTO
import com.example.coremodule.productlist.Category
import com.example.coremodule.productlist.Product
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