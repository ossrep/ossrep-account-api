package org.ossrep.customer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;
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

    public Optional<Customer> findByCustomerId(Long customerId) {
        return this.customerRepository.findByIdOptional(customerId).map(this::toCustomer);
    }

    @Transactional
    public IndividualCustomer create(IndividualCustomer individualCustomer) {
        CustomerEntity entity = toCustomerEntity(individualCustomer);
        this.customerRepository.persist(entity);
        return toIndividualCustomer(entity);
    }

    @Transactional
    public BusinessCustomer create(BusinessCustomer businessCustomer) {
        CustomerEntity entity = toCustomerEntity(businessCustomer);
        this.customerRepository.persist(entity);
        return toBusinessCustomer(entity);
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
        return new IndividualCustomer(entity.customerId, entity.prefix, entity.firstName, entity.middleName, entity.lastName, entity.suffix);
    }

    public BusinessCustomer toBusinessCustomer(CustomerEntity entity) {
        return new BusinessCustomer(entity.customerId, entity.legalName);
    }

    public CustomerEntity toCustomerEntity(IndividualCustomer individualCustomer) {
        CustomerEntity entity = new CustomerEntity();
        entity.customerId = individualCustomer.getCustomerId();
        entity.customerType = individualCustomer.getCustomerType();
        entity.prefix = individualCustomer.prefix();
        entity.firstName = individualCustomer.firstName();
        entity.middleName = individualCustomer.middleName();
        entity.lastName = individualCustomer.lastName();
        entity.suffix = individualCustomer.suffix();
        return entity;
    }

    public CustomerEntity toCustomerEntity(BusinessCustomer businessCustomer) {
        CustomerEntity entity = new CustomerEntity();
        entity.customerId = businessCustomer.getCustomerId();
        entity.customerType = businessCustomer.getCustomerType();
        entity.legalName = businessCustomer.legalName();
        return entity;
    }

}
