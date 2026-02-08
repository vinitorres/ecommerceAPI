package com.vini.torres.ecommerceAPI.application.usecase.user

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UpdateUserUseCase(
    private val userRepository: UserRepository,
    private val getUserUseCase: GetUserUseCase,
    private val passwordEncoder: PasswordEncoder
) {
    @Transactional
    fun execute(id: Long, updatedUser: User): User {
        val existingUser = getUserUseCase.getById(id)
        
        val newPassword = updatedUser.password
        val currentPassword = existingUser.password ?: ""

        var passwordToSave = currentPassword
        if (newPassword.isNotEmpty() && newPassword != currentPassword) {
            // Force non-null assertion because compiler thinks encode returns nullable
            passwordToSave = passwordEncoder.encode(newPassword)!!
        }

        val userToSave = existingUser.copy(
            name = updatedUser.name,
            email = updatedUser.email,
            password = passwordToSave,
            role = updatedUser.role
        )
        return userRepository.save(userToSave)
    }
}
