package com.vini.torres.ecommerceAPI.domain.repository

import com.vini.torres.ecommerceAPI.domain.model.Product
import java.util.Optional

interface ProductRepository {
    fun save(product: Product): Product
    fun findAll(): List<Product>
    fun findById(id: Long): Optional<Product>
    fun deleteById(id: Long)
    fun existsById(id: Long): Boolean
}
