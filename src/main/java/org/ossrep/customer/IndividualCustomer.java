package org.ossrep.customer;

import jakarta.validation.constraints.NotEmpty;

public record IndividualCustomer(
        Long customerId,
        String prefix,
        @NotEmpty(message = "{Customer.firstName.required}")
        String firstName,
        String middleName,
        @NotEmpty(message = "{Customer.lastName.required}")
        String lastName,
        String suffix)
        implements Customer {

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.INDIVIDUAL;
    }

    @Override
    public String getName() {
        return String.format("%s, %s", lastName, firstName);
    }

}
