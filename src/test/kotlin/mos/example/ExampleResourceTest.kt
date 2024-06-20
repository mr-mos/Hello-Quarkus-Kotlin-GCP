package mos.example

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class ExampleResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/")
            .then()
            .statusCode(200)
            .body(`is`("Hello Quarkus, Kotlin and GCP. :))"))
    }

}