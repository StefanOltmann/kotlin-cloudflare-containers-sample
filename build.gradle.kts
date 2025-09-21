plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
}

group = "com.example"
version = "0.0.1"

application {
    mainClass = "ApplicationKt"
}

dependencies {

    /*
     * Ktor server
     */
    implementation(libs.bundles.ktor.server)

    /*
     * Unit Tests
     */
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
