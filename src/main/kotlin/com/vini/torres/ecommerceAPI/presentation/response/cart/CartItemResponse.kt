package com.vini.torres.ecommerceAPI.presentation.response.cart

import com.vini.torres.ecommerceAPI.domain.model.CartItem
import com.vini.torres.ecommerceAPI.presentation.response.ProductResponse
import java.math.BigDecimal

data class CartItemResponse(
    val productId: Long?,
    val productName: String,
    val quantity: Int,
    val price: BigDecimal,
    val total: BigDecimal
) {
    companion object {
        fun fromDomain(item: CartItem): CartItemResponse {
            return CartItemResponse(
                productId = item.product.id,
                productName = item.product.name,
                quantity = item.quantity,
                price = item.price,
                total = item.getTotal()
            )
        }
    }
}
