package com.ossrep.contact;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContactRepository implements PanacheRepositoryBase<ContactEntity, Integer> {
}
