package com.vini.torres.ecommerceAPI.application.usecase.cart

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.repository.CartRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class GetCartUseCase(
    private val cartRepository: CartRepository
) {
    fun execute(userId: Long): Cart {
        return cartRepository.findByUserId(userId)
            .orElseThrow { EntityNotFoundException("Cart not found for user: $userId") }
    }
}
