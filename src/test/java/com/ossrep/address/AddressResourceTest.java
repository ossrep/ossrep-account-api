package com.ossrep.address;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddressResourceTest {

    @Test
    public void getAll() {
        given()
          .when().get("/api/addresses")
          .then()
             .statusCode(200);
    }

}