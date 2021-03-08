package com.ossrep.account;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AccountMapper {

    AccountEntity toEntity(Account domain);

    Account toDomain(AccountEntity entity);

}
