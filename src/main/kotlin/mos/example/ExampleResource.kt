package mos.example

import io.quarkus.runtime.configuration.ConfigUtils
import jakarta.transaction.Transactional
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.net.URI
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Path("/")
class ExampleResource(
    @ConfigProperty(name = "mos.used.version") val quarkusFrameworkVersion: String,
    @ConfigProperty(name = "mos.build.timestamp") val applicationBuildTime: String
) {


    @GET
    @Produces(MediaType.TEXT_HTML)
    fun hello(): String {
        val all: List<MyDomain> = MyDomain.all().list()
        return """<pre>Hello Quarkus ${quarkusFrameworkVersion}, Kotlin and GCP. :)) 
* Server-Start: ${CustomQuarkusStart.getFormattedStartTimeServer()}
* Build-Time: ${convertMavenDate(applicationBuildTime)}
            
My database entries:
  ${all.joinToString("\n  ") { it.name.replace("<", "&lt;").replace(">", "&gt;") }}
   </pre>
 <a href="insert">Add a row to DB table</a> 
 
 <hr>
 <small> ${if (ConfigUtils.getProfiles().contains("dev")) "Check the MySQL Dev Service for the used port: <a href='http://localhost:8080/q/dev-ui/dev-services'>dev-services</a>" else ""} 
 """
    }


    @GET
    @Path("/insert")
    @Transactional
    fun insert(): Response {
        val myDomain = MyDomain()
        myDomain.name = "New ${Date()}"
        myDomain.persist()
        // return with redirect to hello()
        return Response.seeOther(URI.create("/")).build()
    }


    /**
     * Converts a timestamp from the Maven properties to a formatted date and time string; using the local TimeZone
     */
    private fun convertMavenDate(timestamp: String): String {
        val formatter = DateTimeFormatter.ISO_ZONED_DATE_TIME
        val zonedDateTime = ZonedDateTime.parse(timestamp, formatter)
        val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault())
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
    }


}