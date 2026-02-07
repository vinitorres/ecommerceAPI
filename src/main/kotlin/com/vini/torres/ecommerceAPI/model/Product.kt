package com.vini.torres.ecommerceAPI.model

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(length = 500)
    var description: String,

    var imageUrl: String? = null,

    @Column(nullable = false)
    var price: BigDecimal
)