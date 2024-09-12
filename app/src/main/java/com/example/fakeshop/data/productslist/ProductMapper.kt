package com.example.fakeshop.data.productslist

import com.example.fakeshop.domain.productslist.Product
import javax.inject.Inject

class ProductMapper @Inject constructor() {
    fun toProduct(productDTO: ProductDTO) = Product(
        id = productDTO.id,
        name = productDTO.name,
        category = productDTO.category ?: emptyList(),
        price = productDTO.price,
        discountPrice = productDTO.discountPrice ?: productDTO.price,
        images = productDTO.images ?: emptyList(),
        description = productDTO.description ?: ""
    )
}