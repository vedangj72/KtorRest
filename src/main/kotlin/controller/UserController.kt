package com.example.controller

import com.example.core.JwtService
import com.example.models.AuthResponse
import com.example.models.LoginRequest
import com.example.helper.BadRequest
import com.example.helper.ResponseHandler
import com.example.models.User
import com.example.models.UserRequest
import com.example.repository.UserRepository
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*

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

    suspend fun login(call: ApplicationCall) {
        val request = call.receive<LoginRequest>()

        val user = repo.findByEmail(request.email)

        if (user == null || user.password != request.password) {
            call.respond(
                HttpStatusCode.Unauthorized,
                mapOf("message" to "Invalid credentials")
            )
            return
        }

        val accessToken = JwtService.generateAccessToken(user.email)
        val refreshToken = JwtService.generateRefreshToken(user.email)

        call.respond(
            HttpStatusCode.OK,
            AuthResponse(accessToken, refreshToken)
        )
    }
    suspend fun getAllUsers(call: ApplicationCall) {

        val users = repo.getAllUsers()

        call.respond(users)
    }
}
