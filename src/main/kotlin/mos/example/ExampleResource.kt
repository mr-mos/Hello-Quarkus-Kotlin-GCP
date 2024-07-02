package mos.example

import io.quarkus.hibernate.orm.panache.kotlin.PanacheQuery
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
    fun hello(): String {
        val all: List<MyDomain> = MyDomain.all().list()
        return """Hello Quarkus ${quarkusFrameworkVersion}, Kotlin and GCP. :))
            
My database entries:
  ${all.joinToString("\n  ") { it.name }}
            """
    }
}