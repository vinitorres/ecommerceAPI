package com.vini.torres.ecommerceAPI.domain.repository

import com.vini.torres.ecommerceAPI.domain.model.Cart
import java.util.Optional

interface CartRepository {
    fun findByUserId(userId: Long): Optional<Cart>
    fun save(cart: Cart): Cart
    fun deleteByUserId(userId: Long)
}
