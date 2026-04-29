package com.example.helper

import io.ktor.http.HttpStatusCode

open class AppException(
    val statusCode: HttpStatusCode,
    override val message: String
) : RuntimeException(message)

class NotFoundException(message: String = "Not Found") :
    AppException(HttpStatusCode.NotFound, message)

class BadRequestException(message: String = "Bad Request") :
    AppException(HttpStatusCode.BadRequest, message)

class UnauthorizedException(message: String = "Unauthorized") :
    AppException(HttpStatusCode.Unauthorized, message)