package org.ossrep.customer.api.v1;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.ossrep.test.TestData;

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

    @Test
    @TestSecurity(user = "testUser", roles = {Role.CUSTOMER_READ})
    public void getById() {
        CustomerV1 got = given()
                .when()
                .get("/api/v1/customers/{customerId}", 1L)
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract().as(CustomerV1.class);
        Assertions.assertNotNull(got);
        Assertions.assertEquals(new CustomerV1(1L, CustomerTypeV1.INDIVIDUAL, "Costanza, George"), got);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {Role.CUSTOMER_READ})
    public void getByIdNotFound() {
        given()
                .when()
                .get("/api/v1/customers/{customerId}", -1L)
                .then()
                .statusCode(Response.Status.NOT_FOUND.getStatusCode());
    }

    @Test
    @TestSecurity(user = "testUser", roles = {Role.CUSTOMER_WRITE})
    public void postIndividualCustomer() {
        IndividualCustomerV1 customer = new IndividualCustomerV1(null, null,
                TestData.randomString(10), null, TestData.randomString(10), null);
        CustomerV1 saved = given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/api/v1/customers/individual")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract().as(CustomerV1.class);
        Assertions.assertNotNull(saved);
    }

    @Test
    @TestSecurity(user = "testUser", roles = {Role.CUSTOMER_WRITE})
    public void postFailNoFirstName() {
        IndividualCustomerV1 customer = new IndividualCustomerV1(null, null,
                null, null, TestData.randomString(10), null);
        given()
                .contentType(ContentType.JSON)
                .body(customer)
                .post("/api/v1/customers/individual")
                .then()
                .statusCode(Response.Status.BAD_REQUEST.getStatusCode());
    }

}
