package com.vini.torres.ecommerceAPI.presentation.request.cart

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class AddItemRequest(
    @field:NotNull
    val productId: Long,
    
    @field:Min(1)
    val quantity: Int
)
