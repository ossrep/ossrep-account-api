package org.ossrep.customer;

public record BusinessCustomer(Long customerId, String legalName) implements Customer {

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
