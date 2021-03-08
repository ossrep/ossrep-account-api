package com.ossrep.account_type;

import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class AccountTypeService {

    private AccountTypeRepository accountTypeRepository;
    private AccountTypeMapper accountTypeMapper;

    public List<AccountType> findAll(){
        return accountTypeRepository.findAll().stream()
                .map(accountTypeMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<AccountType> findById(Integer accountTypeId) {
        return accountTypeRepository.findByIdOptional(accountTypeId).map(accountTypeMapper::toDomain);
    }

    @Transactional
    public AccountType save(AccountType accountType) {
        AccountTypeEntity entity = accountTypeMapper.toEntity(accountType);
        accountTypeRepository.persist(entity);
        return accountTypeMapper.toDomain(entity);
    }

    @Transactional
    public AccountType update(AccountType accountType) {
        if (accountType.getAccountTypeId() == null) {
            throw new ServiceException("AccountType does not have a accountTypeId");
        }
        Optional<AccountTypeEntity> optional = accountTypeRepository.findByIdOptional(accountType.getAccountTypeId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No AccountType found for accountTypeId[%s]", accountType.getAccountTypeId()));
        }
        AccountTypeEntity entity = optional.get();
        entity.setName(accountType.getName());
        entity.setDescription(accountType.getDescription());
        accountTypeRepository.persist(entity);
        return accountTypeMapper.toDomain(entity);
    }

}