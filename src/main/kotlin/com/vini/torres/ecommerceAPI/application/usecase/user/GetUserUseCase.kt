package com.vini.torres.ecommerceAPI.application.usecase.user

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class GetUserUseCase(private val userRepository: UserRepository) {
    fun getAll(): List<User> {
        return userRepository.findAll()
    }

    fun getById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { EntityNotFoundException("User not found with id: $id") }
    }
}
