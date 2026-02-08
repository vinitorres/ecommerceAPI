package com.vini.torres.ecommerceAPI.infrastructure.persistence.entity

import com.vini.torres.ecommerceAPI.domain.model.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(nullable = false)
    var name: String = ""

    @Column(nullable = false, unique = true)
    var email: String = ""

    @Column(nullable = false)
    var password: String = ""

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = UserRole.USER
}
