package com.vini.torres.ecommerceAPI.application.usecase.cart

import com.vini.torres.ecommerceAPI.application.usecase.product.GetProductUseCase
import com.vini.torres.ecommerceAPI.application.usecase.user.GetUserUseCase
import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.repository.CartRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AddItemToCartUseCase(
    private val cartRepository: CartRepository,
    private val getUserUseCase: GetUserUseCase,
    private val getProductUseCase: GetProductUseCase
) {
    @Transactional
    fun execute(userId: Long, productId: Long, quantity: Int): Cart {
        val cartOptional = cartRepository.findByUserId(userId)
        
        val cart = if (cartOptional.isPresent) {
            cartOptional.get()
        } else {
            val user = getUserUseCase.getById(userId)
            Cart(user = user)
        }

        val product = getProductUseCase.getById(productId)
            .orElseThrow { EntityNotFoundException("Product not found with id: $productId") }

        cart.addItem(product, quantity)
        return cartRepository.save(cart)
    }
}
