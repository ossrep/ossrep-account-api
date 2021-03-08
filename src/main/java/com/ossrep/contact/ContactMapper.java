package com.ossrep.contact;

import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface ContactMapper {

    ContactEntity toEntity(Contact domain);

    Contact toDomain(ContactEntity entity);

}
