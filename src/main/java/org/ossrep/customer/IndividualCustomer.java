package org.ossrep.customer;

public record IndividualCustomer(Long customerId, String prefix, String firstName, String middleName, String lastName, String suffix) implements Customer {

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
