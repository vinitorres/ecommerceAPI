package com.vini.torres.ecommerceAPI.application.usecase.product

import com.vini.torres.ecommerceAPI.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class DeleteProductUseCase(private val repository: ProductRepository) {
    fun execute(id: Long): Boolean {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            true
        } else {
            false
        }
    }
}
