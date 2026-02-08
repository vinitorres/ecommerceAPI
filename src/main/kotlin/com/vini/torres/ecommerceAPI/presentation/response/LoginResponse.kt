package com.vini.torres.ecommerceAPI.presentation.response

data class LoginResponse(
    val token: String,
    val user: UserResponse
)
