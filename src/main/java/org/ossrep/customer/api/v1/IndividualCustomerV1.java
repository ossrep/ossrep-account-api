package org.ossrep.customer.api.v1;

public record IndividualCustomerV1(
        Long customerId,
        String prefix,
        String firstName,
        String middleName,
        String lastName,
        String suffix
) {
}
