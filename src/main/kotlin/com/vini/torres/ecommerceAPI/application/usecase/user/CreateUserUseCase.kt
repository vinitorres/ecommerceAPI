package com.vini.torres.ecommerceAPI.application.usecase.user

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class CreateUserUseCase(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun execute(user: User): User {
        user.password = passwordEncoder.encode(user.password)!!
        return userRepository.save(user)
    }
}
