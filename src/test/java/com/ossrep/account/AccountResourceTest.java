package com.ossrep.account;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AccountResourceTest {

    @Test
    public void getAll() {
        given()
          .when().get("/api/accounts")
          .then()
             .statusCode(200);
    }

}