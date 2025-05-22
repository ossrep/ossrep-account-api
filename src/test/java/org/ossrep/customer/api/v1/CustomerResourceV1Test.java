package org.ossrep.customer.api.v1;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class CustomerResourceV1Test {

    @Test
    @TestSecurity(user = "testUser", roles = {Role.CUSTOMER_READ})
    public void getAll() {
        List<CustomerV1> customers = given()
                .when()
                .get("/api/v1/customers")
                .then()
                .statusCode(200)
                .extract().body().jsonPath().getList(".", CustomerV1.class);
        Assertions.assertNotNull(customers);
        Assertions.assertFalse(customers.isEmpty());
        Assertions.assertTrue(customers.contains(new CustomerV1(1L, CustomerTypeV1.INDIVIDUAL, "Costanza, George")));
        Assertions.assertTrue(customers.contains(new CustomerV1(2L, CustomerTypeV1.BUSINESS, "Vandelay Industries")));
    }

}
