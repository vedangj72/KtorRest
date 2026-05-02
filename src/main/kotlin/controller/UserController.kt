package com.example.controller

import com.example.helper.BadRequest
import com.example.helper.ResponseHandler
import com.example.models.User
import com.example.models.UserRequest
import com.example.repository.UserRepository
import com.example.utils.IdUtils
import com.example.utils.isValidEmail
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.ApplicationCall
import io.ktor.server.request.receive

class UserController(
    private val repo: UserRepository
) {

    suspend fun addUser(call: ApplicationCall) {
        val request = call.receive<UserRequest>()
        if (!request.email.isValidEmail()) {
            throw BadRequest("Invalid email format")
        }
        val user = User(
            id = IdUtils.generateId(),
            name = request.name,
            email = request.email,
            password = request.password
        )
        val createdUser = repo.addUser(user)

        ResponseHandler.success(
            call = call,
            data = createdUser,
            message = "User created successfully",
            status = HttpStatusCode.Created.value
        )
    }
}
