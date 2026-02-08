package com.vini.torres.ecommerceAPI.application.usecase.cart

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.repository.CartRepository
import org.springframework.stereotype.Service

@Service
class ClearCartUseCase(
    private val cartRepository: CartRepository
) {
    fun execute(userId: Long): Cart {
        val cart = cartRepository.findByUserId(userId)
            .orElseThrow { RuntimeException("Cart not found") }
        
        cart.clear()
        return cartRepository.save(cart)
    }
}
