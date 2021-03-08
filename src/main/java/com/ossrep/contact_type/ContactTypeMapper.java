package com.ossrep.contact_type;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ContactTypeMapper {

    ContactTypeEntity toEntity(ContactType domain);

    ContactType toDomain(ContactTypeEntity entity);

}
