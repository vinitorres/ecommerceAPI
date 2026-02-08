package com.vini.torres.ecommerceAPI.infrastructure.persistence.entity

import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "products")
class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    @Column(length = 500)
    var description: String = ""

    var imageUrl: String? = null

    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO
}
