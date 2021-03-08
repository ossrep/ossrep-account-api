package com.ossrep.contact_type;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ContactTypeResourceTest {

    @Test
    public void getAll() {
        given()
          .when().get("/api/contact-types")
          .then()
             .statusCode(200);
    }

}