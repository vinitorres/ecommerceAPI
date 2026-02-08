package com.vini.torres.ecommerceAPI.application.usecase.cart

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.repository.CartRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class RemoveItemFromCartUseCase(
    private val cartRepository: CartRepository
) {
    @Transactional
    fun execute(userId: Long, productId: Long): Cart {
        val cart = cartRepository.findByUserId(userId)
            .orElseThrow { EntityNotFoundException("Cart not found for user: $userId") }
        
        cart.removeItem(productId)
        return cartRepository.save(cart)
    }
}
