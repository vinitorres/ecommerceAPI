package com.vini.torres.ecommerceAPI.infrastructure.persistence.repository

import com.vini.torres.ecommerceAPI.infrastructure.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaUserRepository : JpaRepository<UserEntity, Long>
