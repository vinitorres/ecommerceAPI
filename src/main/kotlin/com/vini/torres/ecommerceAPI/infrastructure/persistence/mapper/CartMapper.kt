package com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper

import com.vini.torres.ecommerceAPI.domain.model.Cart
import com.vini.torres.ecommerceAPI.domain.model.CartItem
import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.CartEntity
import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.CartItemEntity
import org.springframework.stereotype.Component

@Component
class CartMapper(
    private val userMapper: UserMapper,
    private val productMapper: ProductMapper
) {
    fun toDomain(entity: CartEntity): Cart {
        val domainItems = entity.items.map { toDomainItem(it) }.toMutableList()
        return Cart(
            id = entity.id,
            user = userMapper.toDomain(entity.user!!),
            items = domainItems
        )
    }

    private fun toDomainItem(entity: CartItemEntity): CartItem {
        return CartItem(
            id = entity.id,
            product = productMapper.toDomain(entity.product!!),
            quantity = entity.quantity,
            price = entity.price
        )
    }

    fun toEntity(domain: Cart): CartEntity {
        val entity = CartEntity()
        entity.id = domain.id
        entity.user = userMapper.toEntity(domain.user)
        
        val itemEntities = domain.items.map { domainItem ->
            val itemEntity = CartItemEntity()
            itemEntity.id = domainItem.id
           // We assign 'cart' later or ensure bidirectional relationship is handled if necessary
           // But here we are creating NEW entities based on domain. 
           // If we are updating, we should ideally fetch existing and update.
           // For simplicity, we are recreating the list structure.
            itemEntity.cart = entity 
            itemEntity.product = productMapper.toEntity(domainItem.product)
            itemEntity.quantity = domainItem.quantity
            itemEntity.price = domainItem.price
            itemEntity
        }.toMutableList()
        
        entity.items = itemEntities
        return entity
    }
}
