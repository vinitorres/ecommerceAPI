package com.vini.torres.ecommerceAPI.controller

import com.vini.torres.ecommerceAPI.model.Product
import com.vini.torres.ecommerceAPI.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val repository: ProductRepository) {

    @PostMapping
    fun create(@RequestBody product: Product): Product {
        return repository.save(product)
    }

    @GetMapping
    fun listAll(): List<Product> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Product> {
        return repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        return repository.findById(id).map { existingProduct ->
            val productToSave = existingProduct.copy(
                name = updatedProduct.name,
                description = updatedProduct.description,
                imageUrl = updatedProduct.imageUrl,
                price = updatedProduct.price
            )
            ResponseEntity.ok(repository.save(productToSave))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}