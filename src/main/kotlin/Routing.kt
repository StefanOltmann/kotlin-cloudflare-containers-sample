import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import io.ktor.server.application.Application
import io.ktor.server.application.ApplicationCall
import io.ktor.server.application.install
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import io.ktor.server.plugins.origin
import io.ktor.server.response.respondText
import io.ktor.server.routing.get
import io.ktor.server.routing.routing
import kotlinx.serialization.json.Json

fun Application.configureRouting() {

    install(ContentNegotiation) {
        json(Json)
    }

    install(CORS) {

        anyMethod()

        allowHeader(HttpHeaders.AccessControlAllowOrigin)
        allowHeader(HttpHeaders.ContentType)

        anyHost()
    }

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
