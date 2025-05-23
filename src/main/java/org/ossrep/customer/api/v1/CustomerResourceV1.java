package org.ossrep.customer.api.v1;

import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.enums.SchemaType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.ossrep.customer.Customer;
import org.ossrep.customer.CustomerService;
import org.ossrep.customer.CustomerType;
import org.ossrep.customer.IndividualCustomer;

import java.net.URI;

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

    @GET
    @Path("/{customerId}")
    @APIResponse(responseCode = "200", description = "Get Customer by customerId",
            content = @Content(schema = @Schema(implementation = CustomerV1.class))
    )
    @APIResponse(responseCode = "404", description = "Customer does not exist for customerId")
    @RolesAllowed(Role.CUSTOMER_READ)
    public Response getById(@Parameter(name = "customerId", required = true) @PathParam("customerId") Long customerId) {
        return customerService.findByCustomerId(customerId)
                .map(customer -> Response.ok(toApi(customer)).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    @Path("/individual")
    @APIResponse(responseCode = "201", description = "Individual Customer Created",
            content = @Content(schema = @Schema(implementation = IndividualCustomerV1.class))
    )
    @APIResponse(responseCode = "400", description = "Invalid Customer")
    @RolesAllowed(Role.CUSTOMER_WRITE)
    public Response post(@NotNull @Valid IndividualCustomerV1 individualCustomerV1, @Context UriInfo uriInfo) {
        IndividualCustomer created = customerService.create(this.toDomain(individualCustomerV1));
        URI uri = uriInfo.getAbsolutePathBuilder().path(Long.toString(created.customerId())).build();
        return Response.created(uri).entity(this.toApi(created)).build();
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

    private IndividualCustomer toDomain(IndividualCustomerV1 individualCustomerV1) {
        return new IndividualCustomer(individualCustomerV1.customerId(), individualCustomerV1.prefix(), individualCustomerV1.firstName(),
                individualCustomerV1.middleName(), individualCustomerV1.lastName(), individualCustomerV1.suffix());
    }

}
