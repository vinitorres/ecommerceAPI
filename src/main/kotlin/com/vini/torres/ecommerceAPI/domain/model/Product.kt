package com.vini.torres.ecommerceAPI.domain.model

import java.math.BigDecimal

data class Product(
    val id: Long? = null,
    var name: String = "",
    var description: String = "",
    var imageUrl: String? = null,
    var price: BigDecimal = BigDecimal.ZERO
)
