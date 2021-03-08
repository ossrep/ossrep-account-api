package com.ossrep.contact_type;

import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class ContactTypeService {

    private ContactTypeRepository contactTypeRepository;
    private ContactTypeMapper contactTypeMapper;

    public List<ContactType> findAll(){
        return contactTypeRepository.findAll().stream()
                .map(contactTypeMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<ContactType> findById(Integer contactTypeId) {
        return contactTypeRepository.findByIdOptional(contactTypeId).map(contactTypeMapper::toDomain);
    }

    @Transactional
    public ContactType save(ContactType contactType) {
        ContactTypeEntity entity = contactTypeMapper.toEntity(contactType);
        contactTypeRepository.persist(entity);
        return contactTypeMapper.toDomain(entity);
    }

    @Transactional
    public ContactType update(ContactType contactType) {
        if (contactType.getContactTypeId() == null) {
            throw new ServiceException("ContactType does not have a contactTypeId");
        }
        Optional<ContactTypeEntity> optional = contactTypeRepository.findByIdOptional(contactType.getContactTypeId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No ContactType found for contactTypeId[%s]", contactType.getContactTypeId()));
        }
        ContactTypeEntity entity = optional.get();
        entity.setName(contactType.getName());
        entity.setDescription(contactType.getDescription());
        contactTypeRepository.persist(entity);
        return contactTypeMapper.toDomain(entity);
    }

}