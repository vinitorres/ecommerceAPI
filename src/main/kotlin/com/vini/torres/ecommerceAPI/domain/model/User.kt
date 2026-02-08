package com.vini.torres.ecommerceAPI.domain.model

import java.io.Serializable

data class User(
    val id: Long? = null,
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var role: UserRole = UserRole.USER
) : Serializable
