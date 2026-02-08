package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.domain.repository.UserRepository
import com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper.UserMapper
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class UserRepositoryImpl(
    private val jpaUserRepository: JpaUserRepository,
    private val userMapper: UserMapper
) : UserRepository {

    override fun save(user: User): User {
        val entity = userMapper.toEntity(user)
        val savedEntity = jpaUserRepository.save(entity)
        return userMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Optional<User> {
        val entityOptional = jpaUserRepository.findById(id)
        return entityOptional.map { userMapper.toDomain(it) }
    }

    override fun findAll(): List<User> {
        return jpaUserRepository.findAll().map { userMapper.toDomain(it) }
    }

    override fun existsById(id: Long): Boolean {
        return jpaUserRepository.existsById(id)
    }

    override fun deleteById(id: Long) {
        jpaUserRepository.deleteById(id)
    }
}
