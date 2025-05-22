package org.ossrep.customer;

import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> findAll() {
        return this.customerRepository.findAll().stream().map(this::toCustomer).collect(Collectors.toList());
    }

    public Customer toCustomer(CustomerEntity customerEntity) {
        switch (customerEntity.customerType) {
            case BUSINESS -> {
                return toBusinessCustomer(customerEntity);
            }
            case INDIVIDUAL -> {
                return toIndividualCustomer(customerEntity);
            }
            default -> throw new IllegalArgumentException("Unknown customer type: " + customerEntity.customerType);
        }
    }

    public IndividualCustomer toIndividualCustomer(CustomerEntity entity) {
        return new IndividualCustomer(entity.customerId, entity.firstName, entity.middleName, entity.lastName, entity.suffix);
    }

    public BusinessCustomer toBusinessCustomer(CustomerEntity entity) {
        return new BusinessCustomer(entity.customerId, entity.legalName);
    }

}
