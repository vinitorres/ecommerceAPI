package com.vini.torres.ecommerceAPI.presentation.response.cart

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.presentation.response.ProductResponse
import java.math.BigDecimal

data class CartResponse(
    val id: Long?,
    val userId: Long?,
    val items: List<CartItemResponse>,
    val total: BigDecimal
) {
    companion object {
        fun fromDomain(cart: Cart): CartResponse {
            return CartResponse(
                id = cart.id,
                userId = cart.user.id,
                items = cart.items.map { CartItemResponse.fromDomain(it) },
                total = cart.getTotal()
            )
        }
    }
}
