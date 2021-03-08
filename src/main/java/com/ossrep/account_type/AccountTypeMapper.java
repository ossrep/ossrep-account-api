package com.ossrep.account_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AccountTypeMapper {

    AccountTypeEntity toEntity(AccountType domain);

    AccountType toDomain(AccountTypeEntity entity);

}
