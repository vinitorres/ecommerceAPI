package com.vini.torres.ecommerceAPI.application.usecase.product

import com.vini.torres.ecommerceAPI.domain.model.Product
import com.vini.torres.ecommerceAPI.domain.repository.ProductRepository
import org.springframework.stereotype.Service

@Service
class CreateProductUseCase(private val repository: ProductRepository) {
    fun execute(product: Product): Product {
        return repository.save(product)
    }
}
