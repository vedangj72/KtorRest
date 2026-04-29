package com.example.core

import com.example.helper.AppException
import io.ktor.http.HttpStatusCode
import java.io.IOException

object ApiHandler {

    suspend inline fun <T> execute(
        crossinline block: suspend () -> T
    ): T {
        return try {
            block()
        } catch (e: Exception) {
            throw mapToAppException(e)
        }
    }

    fun mapToAppException(e: Exception): AppException {
        return when (e) {

            // Already mapped → just pass through
            is AppException -> e

            // Network / IO issues
            is IOException ->
                AppException(
                    HttpStatusCode.ServiceUnavailable,
                    "Network error, please try again"
                )

            // Timeout (Ktor client)
            is io.ktor.client.network.sockets.SocketTimeoutException ->
                AppException(
                    HttpStatusCode.GatewayTimeout,
                    "Request timeout"
                )

            // Fallback (VERY IMPORTANT)
            else ->
                AppException(
                    HttpStatusCode.InternalServerError,
                    e.message ?: "Something went wrong"
                )
        }
    }
}