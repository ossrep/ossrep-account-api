package com.ossrep.contact_type;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactTypeRepository implements PanacheRepositoryBase<ContactTypeEntity, Integer> {
}
