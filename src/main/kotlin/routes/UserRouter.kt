package com.example.routes

import com.example.controller.UserController
import io.ktor.http.HttpStatusCode
import io.ktor.server.auth.authenticate
import io.ktor.server.request.receive
import io.ktor.server.response.respondText
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import org.koin.ktor.ext.inject
import io.ktor.server.response.respond

fun Route.userRoute() {
    val controller by inject<UserController>()

    route("/users") {
        post {
            controller.addUser(call)
        }
        post("/login") {

            controller.login(call)

        }
        authenticate("auth-jwt") {
            get {

                controller.getAllUsers(call)

            }
            get("/profile") {

                call.respond(mapOf("message" to "Protected API"))

            }

        }
    }
}


