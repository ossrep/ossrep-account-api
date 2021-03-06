package com.ossrep.account_type;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class AccountTypeResourceTest {

    @Test
    public void getAll() {
        given()
          .when().get("/api/account-types")
          .then()
             .statusCode(200);
    }

}