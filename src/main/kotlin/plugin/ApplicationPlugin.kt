package com.example.plugin

import com.example.helper.AppException
import com.example.models.errorResponse
import io.ktor.http.HttpStatusCode
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.statuspages.StatusPages
import io.ktor.server.response.respond

fun Application.configurePlugin(){
    install(ContentNegotiation) {
        json()
    }
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            val status = HttpStatusCode.NotFound
            call.respond(
                status = status,
                message = errorResponse(
                    status = status.value,
                    message = "Route not found"
                )
            )
        }
        exception<AppException> { call, cause ->
            call.respond(
                status = cause.statusCode,
                message = errorResponse(
                    status = cause.statusCode.value,
                    message = cause.message
                )
            )
        }
        exception<Throwable> { call, _ ->
            val status = HttpStatusCode.InternalServerError
            call.respond(
                status = status,
                message = errorResponse(
                    status = status.value,
                    message = "Something went wrong"
                )
            )
        }
    }
}
