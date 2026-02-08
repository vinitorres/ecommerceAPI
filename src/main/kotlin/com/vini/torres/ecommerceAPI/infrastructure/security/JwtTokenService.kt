package com.vini.torres.ecommerceAPI.infrastructure.security

import com.vini.torres.ecommerceAPI.application.port.output.TokenService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.nio.charset.StandardCharsets
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtTokenService(
    @Value("\${jwt.secret:my-secret-key-12345678901234567890123456789012}")
    private val secret: String,
    @Value("\${jwt.expiration:86400000}")
    private val expiration: Long
) : TokenService {

    private fun getSigningKey(): SecretKey {
        return Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
    }

    override fun generateToken(userDetails: UserDetails): String {
        return Jwts.builder()
            .subject(userDetails.username)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSigningKey())
            .compact()
    }

    override fun validateToken(token: String): String {
        return Jwts.parser()
             .verifyWith(getSigningKey())
             .build()
            .parseSignedClaims(token)
            .payload
            .subject
    }
}
