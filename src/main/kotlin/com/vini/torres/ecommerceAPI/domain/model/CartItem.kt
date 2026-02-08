package com.vini.torres.ecommerceAPI.domain.model

import java.math.BigDecimal

data class CartItem(
    val id: Long? = null,
    val product: Product,
    var quantity: Int,
    var price: BigDecimal // Price at the time of adding to cart
) {
    fun getTotal(): BigDecimal = price.multiply(BigDecimal(quantity))
}
