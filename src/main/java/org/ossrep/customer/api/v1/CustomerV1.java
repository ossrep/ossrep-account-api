package org.ossrep.customer.api.v1;

public record CustomerV1(Long customerId, CustomerTypeV1 customerType, String name) {
}
