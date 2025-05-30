package org.ossrep.customer;

import jakarta.validation.constraints.NotEmpty;

public record BusinessCustomer(
        Long customerId,
        @NotEmpty(message = "{Customer.legalName.required}")
        String legalName
) implements Customer {

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    @Override
    public CustomerType getCustomerType() {
        return CustomerType.BUSINESS;
    }

    @Override
    public String getName() {
        return legalName;
    }

}
