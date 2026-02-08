package com.vini.torres.ecommerceAPI.presentation.request

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.model.UserRole

data class UserRequest(
    val name: String,
    val email: String,
    val password: String,
    val role: UserRole
) {
    fun toDomain(): User {
        return User(
            name = name,
            email = email,
            password = password,
            role = role
        )
    }
}
