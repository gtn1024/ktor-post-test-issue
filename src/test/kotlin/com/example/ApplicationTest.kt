package com.example

import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.testing.testApplication
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json

class ApplicationTest {
    @Test
    fun `test home post`() = testApplication {
        application {
            configureRouting()
        }
        val client = createClient {
            this@testApplication.install(ContentNegotiation) {
                json(Json)
            }
        }
        val name = "test"
        client.post("/") {
            contentType(ContentType.Application.Json)
            setBody(MyData(name))
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}