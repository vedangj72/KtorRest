package com.example.routes

import com.example.models.successResponse
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get

fun Route.healthCheck(){
    get("/") {
        println("hello")
        call.respond(
            status = HttpStatusCode.OK,
            message = successResponse(
                data = "Hello, World bhurrrx",
                status = HttpStatusCode.OK.value,
                message = "Success"
            )
        )
    }
}
