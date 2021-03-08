package com.ossrep.address_type;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AddressTypeResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/api/address-types")
          .then()
             .statusCode(200);
    }

}