package com.vini.torres.ecommerceAPI.infrastructure.security

import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val domainUser = userRepository.findAll().find { it.email == email }
            ?: throw UsernameNotFoundException("User not found with email: $email")

        return CustomUserDetails(domainUser)
    }
}
