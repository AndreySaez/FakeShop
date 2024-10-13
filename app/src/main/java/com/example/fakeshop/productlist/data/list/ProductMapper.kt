package com.example.fakeshop.productlist.data.list

import com.example.fakeshop.productlist.domain.list.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toProduct(productDTO: ProductDTO) = Product(
        id = productDTO.id,
        name = productDTO.title,
        category = productDTO.category,
        price = productDTO.price,
        images = productDTO.images,
        description = productDTO.description
    )
}