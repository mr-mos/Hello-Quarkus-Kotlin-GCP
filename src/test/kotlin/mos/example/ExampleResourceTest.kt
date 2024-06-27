package mos.example

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.eclipse.microprofile.config.ConfigProvider
import org.eclipse.microprofile.config.inject.ConfigProperty
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test


@QuarkusTest
class ExampleResourceTest  {



    @Test
    fun testHelloEndpoint() {
        val quarkusFrameworkVersion =  ConfigProvider.getConfig().getConfigValue("quarkus.used.version").value
        given()
            .`when`().get("/")
            .then()
            .statusCode(200)
            .body(`is`("Hello Quarkus ${quarkusFrameworkVersion?:"NO VERSION"}, Kotlin and GCP. :))"))
    }

}