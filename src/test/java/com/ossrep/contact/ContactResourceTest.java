package com.ossrep.contact;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class ContactResourceTest {

    @Test
    public void getAll() {
        given()
          .when().get("/api/contacts")
          .then()
             .statusCode(200);
    }

}