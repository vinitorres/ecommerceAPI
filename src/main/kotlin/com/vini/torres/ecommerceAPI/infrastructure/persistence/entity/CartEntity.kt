package com.vini.torres.ecommerceAPI.infrastructure.persistence.entity

import jakarta.persistence.*

@Entity
@Table(name = "carts")
class CartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false, unique = true)
    var user: UserEntity? = null

    @OneToMany(mappedBy = "cart", cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER) // Eager loading for simplicity in this case
    var items: MutableList<CartItemEntity> = mutableListOf()
}
