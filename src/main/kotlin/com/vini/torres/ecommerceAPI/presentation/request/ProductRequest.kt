package com.vini.torres.ecommerceAPI.presentation.request

import com.vini.torres.ecommerceAPI.domain.model.Product
import java.math.BigDecimal

data class ProductRequest(
    val name: String,
    val description: String,
    val imageUrl: String?,
    val price: BigDecimal
) {
    fun toDomain(): Product {
        return Product(
            name = name,
            description = description,
            imageUrl = imageUrl,
            price = price
        )
    }
}
