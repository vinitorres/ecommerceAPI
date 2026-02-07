package com.vini.torres.ecommerceAPI.repository

import com.vini.torres.ecommerceAPI.model.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository: JpaRepository<Product, Long>