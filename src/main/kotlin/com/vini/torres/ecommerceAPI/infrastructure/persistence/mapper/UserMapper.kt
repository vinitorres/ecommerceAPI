package com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper

import com.vini.torres.ecommerceAPI.domain.model.User
import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.UserEntity
import org.springframework.stereotype.Component

@Component
class UserMapper {
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            name = entity.name,
            email = entity.email,
            password = entity.password,
            role = entity.role
        )
    }

    fun toEntity(domain: User): UserEntity {
        val entity = UserEntity()
        entity.id = domain.id
        entity.name = domain.name
        entity.email = domain.email
        entity.password = domain.password
        entity.role = domain.role
        return entity
    }
}
