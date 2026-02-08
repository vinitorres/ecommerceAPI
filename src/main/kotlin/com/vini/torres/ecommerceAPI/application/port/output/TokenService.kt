package com.vini.torres.ecommerceAPI.application.port.output

import org.springframework.security.core.userdetails.UserDetails

interface TokenService {
    fun generateToken(userDetails: UserDetails): String
    fun validateToken(token: String): String
}
