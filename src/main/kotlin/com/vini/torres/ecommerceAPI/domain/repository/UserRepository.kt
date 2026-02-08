package com.vini.torres.ecommerceAPI.domain.repository

import com.vini.torres.ecommerceAPI.domain.model.User
import java.util.Optional

interface UserRepository {
    fun save(user: User): User
    fun findById(id: Long): Optional<User>
    fun findAll(): List<User>
    fun existsById(id: Long): Boolean
    fun deleteById(id: Long)
}
