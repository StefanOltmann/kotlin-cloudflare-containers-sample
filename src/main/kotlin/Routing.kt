package com.example

import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.plugins.origin
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing

fun Application.configureRouting() {

    routing {

        get("/") {
            call.respondText("Hello Kotlin!")
        }

        get("/info") {

            val requestDetails = buildString {
                appendLine("Method: ${call.request.local.method}")
                appendLine("URI: ${call.request.local.uri}")
                appendLine("Headers:")
                call.request.headers.forEach { name, values ->
                    appendLine("  $name: ${values.joinToString(", ")}")
                }
                appendLine("Query Parameters:")
                call.request.queryParameters.forEach { name, values ->
                    appendLine("  $name: ${values.joinToString(", ")}")
                }
                appendLine("Remote Address: ${call.request.origin.remoteAddress}")
                appendLine("Client IP: ${call.getIpAddress()}")
            }

            call.respondText(requestDetails)
        }
    }
}

private fun ApplicationCall.getIpAddress(): String =
    request.headers["X-Real-IP"] ?: request.origin.remoteAddress
