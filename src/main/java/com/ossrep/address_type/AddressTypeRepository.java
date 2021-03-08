package com.ossrep.address_type;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AddressTypeRepository implements PanacheRepositoryBase<AddressTypeEntity, Integer> {
}
