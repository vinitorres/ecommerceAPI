package com.vini.torres.ecommerceAPI.presentation.response

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.model.UserRole

data class UserResponse(
    val id: Long?,
    val name: String,
    val email: String,
    val role: UserRole
) {
    companion object {
        fun fromDomain(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                name = user.name,
                email = user.email,
                role = user.role
            )
        }
    }
}
