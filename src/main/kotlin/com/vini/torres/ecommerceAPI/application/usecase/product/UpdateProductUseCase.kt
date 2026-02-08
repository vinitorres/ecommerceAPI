package com.vini.torres.ecommerceAPI.application.usecase.product

import com.vini.torres.ecommerceAPI.domain.model.Product
import com.vini.torres.ecommerceAPI.domain.repository.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class UpdateProductUseCase(private val repository: ProductRepository) {
    @Transactional
    fun execute(id: Long, updatedProduct: Product): Optional<Product> {
        return repository.findById(id).map { existingProduct ->
            val productToSave = existingProduct.copy(
                name = updatedProduct.name,
                description = updatedProduct.description,
                imageUrl = updatedProduct.imageUrl,
                price = updatedProduct.price
            )
            repository.save(productToSave)
        }
    }
}
