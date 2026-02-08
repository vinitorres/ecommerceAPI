package com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper

import com.vini.torres.ecommerceAPI.domain.model.Product
import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.ProductEntity
import org.springframework.stereotype.Component

@Component
class ProductMapper {
    fun toDomain(entity: ProductEntity): Product {
        return Product(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            imageUrl = entity.imageUrl,
            price = entity.price
        )
    }

    fun toEntity(domain: Product): ProductEntity {
        val entity = ProductEntity()
        entity.id = domain.id
        entity.name = domain.name
        entity.description = domain.description
        entity.imageUrl = domain.imageUrl
        entity.price = domain.price
        return entity
    }
}
