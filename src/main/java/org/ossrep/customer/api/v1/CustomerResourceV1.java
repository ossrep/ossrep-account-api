package org.ossrep.customer.api.v1;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.ossrep.customer.Customer;
import org.ossrep.customer.CustomerService;
import org.ossrep.customer.CustomerType;

@Path("/api/v1/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "customer", description = "Customer Operations")
public class CustomerResourceV1 {

    private final CustomerService customerService;

    public CustomerResourceV1(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GET
    @APIResponse(responseCode = "200", description = "Get All Customers",
            content = @Content(schema = @Schema(type = SchemaType.ARRAY, implementation = CustomerV1.class))
    )
    @RolesAllowed(Role.CUSTOMER_READ)
    public Response get() {
        return Response.ok(customerService.findAll().stream().map(this::toApi)).build();
    }

    private CustomerV1 toApi(Customer customer) {
        return new CustomerV1(customer.getCustomerId(), toApi(customer.getCustomerType()), customer.getName());
    }

    private CustomerTypeV1 toApi(CustomerType customer) {
        switch (customer) {
            case INDIVIDUAL -> {
                return CustomerTypeV1.INDIVIDUAL;
            }
            case BUSINESS -> {
                return CustomerTypeV1.BUSINESS;
            }
            default -> throw new IllegalStateException("Unexpected value: " + customer);
        }
    }

}
