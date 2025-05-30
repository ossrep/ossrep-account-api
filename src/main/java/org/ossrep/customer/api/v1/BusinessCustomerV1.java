package org.ossrep.customer.api.v1;

import jakarta.validation.constraints.NotEmpty;

public record BusinessCustomerV1(
        Long customerId,
        @NotEmpty(message = "{Customer.legalName.required}")
        String legalName) {
}
