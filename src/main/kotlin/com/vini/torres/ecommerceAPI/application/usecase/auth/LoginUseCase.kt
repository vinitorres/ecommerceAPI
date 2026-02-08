package com.vini.torres.ecommerceAPI.application.usecase.auth

import com.vini.torres.ecommerceAPI.application.port.output.TokenService
import com.vini.torres.ecommerceAPI.infrastructure.security.CustomUserDetails
import com.vini.torres.ecommerceAPI.presentation.request.LoginRequest
import com.vini.torres.ecommerceAPI.presentation.response.LoginResponse
import com.vini.torres.ecommerceAPI.presentation.response.UserResponse
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
        // Authenticate user
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(request.email, request.password)
        )

        // Get authenticated user details
        val userDetails = userDetailsService.loadUserByUsername(request.email) as CustomUserDetails
        val token = tokenService.generateToken(userDetails)
        
        // Extract user data from CustomUserDetails
        val user = userDetails.getUser()
        val userResponse = UserResponse.fromDomain(user)
        
        return LoginResponse(token, userResponse)
    }
}
