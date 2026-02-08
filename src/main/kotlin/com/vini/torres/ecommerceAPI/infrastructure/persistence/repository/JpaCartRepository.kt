package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.CartEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
interface JpaCartRepository : JpaRepository<CartEntity, Long> {
    fun findByUserId(userId: Long): Optional<CartEntity>
}
