package com.ossrep.contact;

import com.ossrep.account.AccountRepository;
import com.ossrep.contact_type.ContactTypeRepository;
import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class ContactService {

    private ContactRepository contactRepository;
    private ContactTypeRepository contactTypeRepository;
    private AccountRepository accountRepository;
    private ContactMapper contactMapper;

    public List<Contact> findAll(){
        return contactRepository.findAll().stream()
                .map(contactMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Contact> findById(Integer contactId) {
        return contactRepository.findByIdOptional(contactId).map(contactMapper::toDomain);
    }

    @Transactional
    public Contact save(Contact contact) {
        ContactEntity entity = contactMapper.toEntity(contact);
        contactRepository.persist(entity);
        return contactMapper.toDomain(entity);
    }

    @Transactional
    public Contact update(Contact contact) {
        if (contact.getContactId() == null) {
            throw new ServiceException("Contact does not have a contactId");
        }
        Optional<ContactEntity> optional = contactRepository.findByIdOptional(contact.getContactId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No Contact found for contactId[%s]", contact.getContactId()));
        }
        ContactEntity entity = optional.get();
        entity.setContactType(contactTypeRepository.findById(contact.getContactType().getContactTypeId()));
        entity.setAccount(accountRepository.findById(contact.getAccount().getAccountId()));
        entity.setFirstName(contact.getFirstName());
        entity.setMiddleName(contact.getMiddleName());
        entity.setLastName(contact.getLastName());
        entity.setSuffix(contact.getSuffix());
        entity.setEmail(contact.getEmail());
        entity.setPhone(contact.getPhone());
        contactRepository.persist(entity);
        return contactMapper.toDomain(entity);
    }

}