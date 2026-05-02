package com.example.models

import kotlinx.serialization.Serializable

@Serializable
data class Response<T>(
    val data: T?,
    val status: Int,
    val message: String,
)

fun <T> successResponse(
    data: T?,
    status: Int,
    message: String
): Response<T> = Response(
    data = data,
    status = status,
    message = message
)

fun errorResponse(
    status: Int,
    message: String
): Response<Unit> = Response(
    data = null,
    status = status,
    message = message
)
