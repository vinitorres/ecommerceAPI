package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.domain.model.Product
import com.vini.torres.ecommerceAPI.domain.repository.ProductRepository
import com.vini.torres.ecommerceAPI.infrastructure.persistence.mapper.ProductMapper
import org.springframework.stereotype.Component
import java.util.Optional

@Component
class ProductRepositoryImpl(
    private val jpaRepository: JpaProductRepository,
    private val mapper: ProductMapper
) : ProductRepository {

    override fun save(product: Product): Product {
        val entity = mapper.toEntity(product)
        val savedEntity = jpaRepository.save(entity)
        return mapper.toDomain(savedEntity)
    }

    override fun findAll(): List<Product> {
        return jpaRepository.findAll().map { mapper.toDomain(it) }
    }

    override fun findById(id: Long): Optional<Product> {
        return jpaRepository.findById(id).map { mapper.toDomain(it) }
    }

    override fun deleteById(id: Long) {
        jpaRepository.deleteById(id)
    }

    override fun existsById(id: Long): Boolean {
        return jpaRepository.existsById(id)
    }
}
