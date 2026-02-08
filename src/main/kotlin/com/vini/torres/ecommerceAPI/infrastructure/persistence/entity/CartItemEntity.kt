package com.vini.torres.ecommerceAPI.infrastructure.persistence.entity

import com.vini.torres.ecommerceAPI.domain.model.Product
import jakarta.persistence.*
import java.math.BigDecimal

@Entity
@Table(name = "cart_items")
class CartItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    var cart: CartEntity? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    var product: ProductEntity? = null

    @Column(nullable = false)
    var quantity: Int = 1

    @Column(nullable = false)
    var price: BigDecimal = BigDecimal.ZERO
}
