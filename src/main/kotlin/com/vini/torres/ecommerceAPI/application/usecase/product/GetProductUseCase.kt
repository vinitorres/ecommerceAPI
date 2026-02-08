package com.vini.torres.ecommerceAPI.application.usecase.product

import com.vini.torres.ecommerceAPI.domain.model.Product
import com.vini.torres.ecommerceAPI.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class GetProductUseCase(private val repository: ProductRepository) {
    fun getAll(): List<Product> {
        return repository.findAll()
    }

    fun getById(id: Long): Optional<Product> {
        return repository.findById(id)
    }
}
