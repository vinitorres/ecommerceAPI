package com.vini.torres.ecommerceAPI.domain.model

import java.math.BigDecimal

data class Cart(
    val id: Long? = null,
    val user: User,
    val items: MutableList<CartItem> = mutableListOf()
) {
    fun getTotal(): BigDecimal {
        return items.fold(BigDecimal.ZERO) { acc, item -> acc.add(item.getTotal()) }
    }

    fun addItem(product: Product, quantity: Int) {
        val existingItem = items.find { it.product.id == product.id }
        if (existingItem != null) {
            existingItem.quantity += quantity
        } else {
            items.add(CartItem(
                product = product, 
                quantity = quantity, 
                price = product.price
            ))
        }
    }

    fun removeItem(productId: Long) {
        items.removeIf { it.product.id == productId }
    }

    fun updateQuantity(productId: Long, quantity: Int) {
        if (quantity <= 0) {
            removeItem(productId)
            return
        }

        val item = items.find { it.product.id == productId }
        item?.let {
            it.quantity = quantity
        }
    }
    
    fun clear() {
        items.clear()
    }
}
