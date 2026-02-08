package com.vini.torres.ecommerceAPI.application.usecase.user

import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service

@Service
class DeleteUserUseCase(private val userRepository: UserRepository) {
    fun execute(id: Long) {
        if (!userRepository.existsById(id)) {
            throw EntityNotFoundException("User not found with id: $id")
        }
        userRepository.deleteById(id)
    }
}
