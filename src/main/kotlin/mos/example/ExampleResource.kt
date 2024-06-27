package mos.example

import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import org.eclipse.microprofile.config.inject.ConfigProperty

@Path("/")
class ExampleResource(
    @ConfigProperty(name = "quarkus.used.version") val quarkusFrameworkVersion: String
) {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun hello() = "Hello Quarkus ${quarkusFrameworkVersion}, Kotlin and GCP. :))"
}