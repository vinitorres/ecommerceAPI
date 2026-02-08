package com.vini.torres.ecommerceAPI.application.usecase.auth

import com.vini.torres.ecommerceAPI.application.port.output.TokenService
import com.vini.torres.ecommerceAPI.presentation.request.LoginRequest
import com.vini.torres.ecommerceAPI.presentation.response.LoginResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class LoginUseCase(
    private val authenticationManager: AuthenticationManager,
    private val tokenService: TokenService,
    private val userDetailsService: UserDetailsService
) {

    fun execute(request: LoginRequest): LoginResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        val userDetails = userDetailsService.loadUserByUsername(request.email)
        val token = tokenService.generateToken(userDetails)
        
        return LoginResponse(token)
    }
}
