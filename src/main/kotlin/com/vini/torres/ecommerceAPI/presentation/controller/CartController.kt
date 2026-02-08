package com.vini.torres.ecommerceAPI.presentation.controller

import com.vini.torres.ecommerceAPI.application.usecase.cart.AddItemToCartUseCase
import com.vini.torres.ecommerceAPI.application.usecase.cart.GetCartUseCase
import com.vini.torres.ecommerceAPI.application.usecase.cart.RemoveItemFromCartUseCase
import com.vini.torres.ecommerceAPI.presentation.request.cart.AddItemRequest
import com.vini.torres.ecommerceAPI.presentation.response.cart.CartResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.*
import com.vini.torres.ecommerceAPI.domain.repository.UserRepository

@RestController
@RequestMapping("/cart")
class CartController(
    private val addItemToCartUseCase: AddItemToCartUseCase,
    private val getCartUseCase: GetCartUseCase,
    private val removeItemFromCartUseCase: RemoveItemFromCartUseCase,
    private val userRepository: UserRepository
) {

    private fun getUserIdFromPrincipal(userDetails: UserDetails): Long {
        // In a real scenario, we might store ID in UserDetails or look it up efficiently.
        // For now, look up by email to be safe and consistent with current architecture.
        return userRepository.findAll().find { it.email == userDetails.username }?.id 
            ?: throw RuntimeException("User not found")
    }

    @GetMapping
    fun getCart(@AuthenticationPrincipal userDetails: UserDetails): ResponseEntity<CartResponse> {
        val userId = getUserIdFromPrincipal(userDetails)
        return try {
            val cart = getCartUseCase.execute(userId)
            ResponseEntity.ok(CartResponse.fromDomain(cart))
        } catch (e: Exception) {
            // Cart might not exist yet, return empty structure or 404
             ResponseEntity.notFound().build()
        }
    }

    @PostMapping("/items")
    fun addItem(
        @AuthenticationPrincipal userDetails: UserDetails,
        @RequestBody request: AddItemRequest
    ): ResponseEntity<CartResponse> {
        val userId = getUserIdFromPrincipal(userDetails)
        val cart = addItemToCartUseCase.execute(userId, request.productId, request.quantity)
        return ResponseEntity.ok(CartResponse.fromDomain(cart))
    }

    @DeleteMapping("/items/{productId}")
    fun removeItem(
        @AuthenticationPrincipal userDetails: UserDetails,
        @PathVariable productId: Long
    ): ResponseEntity<CartResponse> {
        val userId = getUserIdFromPrincipal(userDetails)
        val cart = removeItemFromCartUseCase.execute(userId, productId)
        return ResponseEntity.ok(CartResponse.fromDomain(cart))
    }
}
