package com.vini.torres.ecommerceAPI.presentation.controller

import com.vini.torres.ecommerceAPI.application.usecase.product.CreateProductUseCase
import com.vini.torres.ecommerceAPI.application.usecase.product.DeleteProductUseCase
import com.vini.torres.ecommerceAPI.application.usecase.product.GetProductUseCase
import com.vini.torres.ecommerceAPI.application.usecase.product.UpdateProductUseCase
import com.vini.torres.ecommerceAPI.presentation.request.ProductRequest
import com.vini.torres.ecommerceAPI.presentation.response.ProductResponse
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/products")
class ProductController(
    private val createProductUseCase: CreateProductUseCase,
    private val getProductUseCase: GetProductUseCase,
    private val updateProductUseCase: UpdateProductUseCase,
    private val deleteProductUseCase: DeleteProductUseCase
) {

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    fun create(@RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        val createdProduct = createProductUseCase.execute(request.toDomain())
        return ResponseEntity.ok(ProductResponse.fromDomain(createdProduct))
    }

    @GetMapping
    fun listAll(): ResponseEntity<List<ProductResponse>> {
        val products = getProductUseCase.getAll().map { ProductResponse.fromDomain(it) }
        return ResponseEntity.ok(products)
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ResponseEntity<ProductResponse> {
        return getProductUseCase.getById(id)
            .map { ResponseEntity.ok(ProductResponse.fromDomain(it)) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody request: ProductRequest): ResponseEntity<ProductResponse> {
        return updateProductUseCase.execute(id, request.toDomain())
            .map { ResponseEntity.ok(ProductResponse.fromDomain(it)) }
            .orElse(ResponseEntity.notFound().build())
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        return if (deleteProductUseCase.execute(id)) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}
