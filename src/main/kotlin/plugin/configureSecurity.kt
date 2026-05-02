package com.example.plugin


import com.example.core.JwtService
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*

fun Application.configureSecurity() {

    install(Authentication) {
        jwt("auth-jwt") {

            verifier(JwtService.verifier)

            validate { credential ->
                val email = credential.payload.getClaim("email").asString()
                if (!email.isNullOrBlank()) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}