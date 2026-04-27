package com.example.routes

import io.ktor.http.HttpStatusCode
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.post

fun Route.userRoute(){
    post("/postReq") {
        val data = call.receive<PostRequest>()
        println(data)
        call.respondText(
            text = data.name,
            status = HttpStatusCode.Created
        )
    }
}