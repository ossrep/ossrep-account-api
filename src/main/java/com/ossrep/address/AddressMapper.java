package com.ossrep.address;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AddressMapper {

    AddressEntity toEntity(Address domain);

    Address toDomain(AddressEntity entity);

}
