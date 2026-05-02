package com.example.helper

import com.example.models.Response
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.response.respond

object ResponseHandler {
    suspend inline fun <reified T> success(
        call: ApplicationCall,
        data: T?,
        message: String = "Success",
        status: Int = 200
    ) {
        call.respond(
            status = HttpStatusCode.fromValue(status),
            message = Response(
                data = data,
                status = status,
                message = message
            )
        )
    }
}
