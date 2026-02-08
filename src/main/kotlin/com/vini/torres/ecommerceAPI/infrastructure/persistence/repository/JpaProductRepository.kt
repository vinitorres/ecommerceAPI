package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.ProductEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaProductRepository : JpaRepository<ProductEntity, Long>
