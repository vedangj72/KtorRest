package com.example

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlin.test.*

class ServerTest {

    @Test
    fun `test root endpoint`() = testApplication {
        configure()

        val response = client.get("/")
        val body = response.body<String>()

        assertEquals(HttpStatusCode.OK, response.status)
        assertTrue(body.contains(""""data":"Hello, World bhurrrx""""))
        assertTrue(body.contains(""""status":200"""))
        assertTrue(body.contains(""""message":"Success""""))
    }

    @Test
    fun `test not found endpoint returns response wrapper`() = testApplication {
        configure()

        val response = client.get("/missing")
        val body = response.body<String>()

        assertEquals(HttpStatusCode.NotFound, response.status)
        assertTrue(body.contains(""""data":null"""))
        assertTrue(body.contains(""""status":404"""))
        assertTrue(body.contains(""""message":"Route not found""""))
    }

    @Test
    fun `test user validation error returns response wrapper`() = testApplication {
        configure()

        val response = client.post("/users") {
            contentType(ContentType.Application.Json)
            setBody(
                """
                {
                  "id": "1",
                  "name": "Vedang",
                  "email": "not-an-email",
                  "password": "secret"
                }
                """.trimIndent()
            )
        }
        val body = response.body<String>()

        assertEquals(HttpStatusCode.BadRequest, response.status)
        assertTrue(body.contains(""""data":null"""))
        assertTrue(body.contains(""""status":400"""))
        assertTrue(body.contains(""""message":"Invalid email format""""))
    }

}
