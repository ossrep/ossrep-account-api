package com.ossrep.address_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface AddressTypeMapper {

    AddressTypeEntity toEntity(AddressType domain);

    AddressType toDomain(AddressTypeEntity entity);

}
