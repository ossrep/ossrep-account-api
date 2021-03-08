package com.ossrep.account_type;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AccountTypeRepository implements PanacheRepositoryBase<AccountTypeEntity, Integer> {
}
