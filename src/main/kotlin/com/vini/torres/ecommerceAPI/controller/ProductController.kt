package com.vini.torres.ecommerceAPI.controller

import com.vini.torres.ecommerceAPI.model.Product
import com.vini.torres.ecommerceAPI.repository.ProductRepository
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(private val repository: ProductRepository) {

    @PostMapping("/add")
    fun create(@RequestBody product: Product): Product {
        return repository.save(product)
    }

    @GetMapping("/all")
    fun listAll(): List<Product> {
        return repository.findAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<Product> {
        return repository.findById(id)
            .map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PutMapping("/update/{id}")
    fun update(@PathVariable id: Long, @RequestBody updatedProduct: Product): ResponseEntity<Product> {
        return repository.findById(id).map { existingProduct ->
            existingProduct.name = updatedProduct.name
            existingProduct.description = updatedProduct.description
            existingProduct.imageUrl = updatedProduct.imageUrl
            existingProduct.price = updatedProduct.price
            ResponseEntity.ok(repository.save(existingProduct))
        }.orElse(ResponseEntity.notFound().build())
    }

    @DeleteMapping("/delete/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (repository.existsById(id)) {
            repository.deleteById(id)
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}