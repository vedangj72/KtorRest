package com.example.core

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

object JwtService {

    private const val SECRET = "my-super-secret"
    private const val ISSUER = "ktor-app"
    private const val AUDIENCE = "ktor-users"

    private val algorithm = Algorithm.HMAC256(SECRET)

    val verifier = JWT
        .require(algorithm)
        .withIssuer(ISSUER)
        .withAudience(AUDIENCE)
        .build()

    fun generateAccessToken(email: String): String =
        JWT.create()
            .withAudience(AUDIENCE)
            .withIssuer(ISSUER)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 15))
            .sign(algorithm)

    fun generateRefreshToken(email: String): String =
        JWT.create()
            .withAudience(AUDIENCE)
            .withIssuer(ISSUER)
            .withClaim("email", email)
            .withExpiresAt(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .sign(algorithm)
}