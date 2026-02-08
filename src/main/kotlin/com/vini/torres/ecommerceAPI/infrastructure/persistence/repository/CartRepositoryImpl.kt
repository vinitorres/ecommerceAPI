package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.repository.CartRepository
import com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper.CartMapper
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class CartRepositoryImpl(
    private val jpaCartRepository: JpaCartRepository,
    private val cartMapper: CartMapper
) : CartRepository {

    override fun findByUserId(userId: Long): Optional<Cart> {
        val entity = jpaCartRepository.findByUserId(userId)
        return entity.map { cartMapper.toDomain(it) }
    }

    override fun save(cart: Cart): Cart {
        val entity = cartMapper.toEntity(cart)
        // If updating, we might need to handle merging items properly, 
        // but for now relying on JPA cascade + orphanRemoval
        val savedEntity = jpaCartRepository.save(entity)
        return cartMapper.toDomain(savedEntity)
    }

    override fun deleteByUserId(userId: Long) {
       val cart = jpaCartRepository.findByUserId(userId)
       if (cart.isPresent) {
           jpaCartRepository.delete(cart.get())
       }
    }
}
