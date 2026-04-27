package com.example

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.get
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.server.testing.testApplication
import kotlin.test.*

class ServerTest {

    @Test
    fun `test root endpoint`() = testApplication {
        // loads default configuration
        configure()
        // verify server root returns 200
        assertEquals(HttpStatusCode.OK, client.get("/").status)
    }

    @Test
    fun `test post endpoint accepts json body`() = testApplication {
        configure()

        val response = client.post("/postReq") {
            contentType(ContentType.Application.Json)
            setBody("""{"name":"Vedang"}""")
        }

        assertEquals(HttpStatusCode.Created, response.status)
    }

}
