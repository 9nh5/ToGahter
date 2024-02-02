package com.teamsparta.togahter.domain.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.time.Instant
import java.util.*
import java.time.Duration

@Component
class TokenProvider(
    @Value("\${auth.jwt.issuer}")
    private val issuer: String,

    @Value("\${auth.jwt.secret}")
    private val secret: String,

//    @Value("\${auth.jwt.accessTokenExpirationHour}")
//    private val accessTokenExpirationHour: Long
) {
    private fun generateToken(
        subject: String,
        email: String,
        nickname: String,
        role: String,
    ): String {

        val claims: Claims = Jwts.claims()
            .add(mapOf("role" to role, "email" to email, "nickname" to nickname))
            .build()

        val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))

        val now = Instant.now()

        return Jwts.builder()
            .subject(subject)
            .issuer(issuer)
            .issuedAt(Date.from(now))
            .expiration(Date.from(now.plus(Duration.ofHours(1))))
            .claims(claims)
            .signWith(key)
            .compact()
    }

    fun generateAccessToken(
        subject: String,
        email: String,
        role: String,
        nickname: String
    ): String{
        return generateToken(subject, email, nickname, role)
    }

    fun validateToken(jwt: String): Result<Jws<Claims>> {
        return kotlin.runCatching {
            val key = Keys.hmacShaKeyFor(secret.toByteArray(StandardCharsets.UTF_8))
            Jwts
                .parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
        }
    }
}