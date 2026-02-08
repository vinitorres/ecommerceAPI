package com.vini.torres.ecommerceAPI.presentation.response

import com.vini.torres.ecommerceAPI.domain.model.Product
import java.math.BigDecimal

data class ProductResponse(
    val id: Long?,
    val name: String,
    val description: String,
    val imageUrl: String?,
    val price: BigDecimal
) {
    companion object {
        fun fromDomain(product: Product): ProductResponse {
            return ProductResponse(
                id = product.id,
                name = product.name,
                description = product.description,
                imageUrl = product.imageUrl,
                price = product.price
            )
        }
    }
}
