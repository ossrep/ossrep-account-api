package org.ossrep.customer.api.v1;

import jakarta.validation.constraints.NotEmpty;

public record IndividualCustomerV1(
        Long customerId,
        String prefix,
        @NotEmpty(message = "{Customer.firstName.required}")
        String firstName,
        String middleName,
        @NotEmpty(message = "{Customer.lastName.required}")
        String lastName,
        String suffix
) {
}
