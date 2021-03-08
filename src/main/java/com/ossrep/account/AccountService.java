package com.ossrep.account;

import com.ossrep.account_type.AccountType;
import com.ossrep.account_type.AccountTypeRepository;
import com.ossrep.exception.ServiceException;
import lombok.AllArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ApplicationScoped
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;
    private AccountTypeRepository accountTypeRepository;
    private AccountMapper accountMapper;

    public List<Account> findAll(){
        return accountRepository.findAll().stream()
                .map(accountMapper::toDomain)
                .collect(Collectors.toList());
    }

    public Optional<Account> findById(Integer accountId) {
        return accountRepository.findByIdOptional(accountId).map(accountMapper::toDomain);
    }

    @Transactional
    public Account save(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        accountRepository.persist(entity);
        return accountMapper.toDomain(entity);
    }

    @Transactional
    public Account update(Account account) {
        if (account.getAccountId() == null) {
            throw new ServiceException("Account does not have a accountId");
        }
        Optional<AccountEntity> optional = accountRepository.findByIdOptional(account.getAccountId());
        if (optional.isEmpty()) {
            throw new ServiceException(String.format("No Account found for accountId[%s]", account.getAccountId()));
        }
        AccountEntity entity = optional.get();
        entity.setAccountType(accountTypeRepository.findById(account.getAccountType().getAccountTypeId()));
        entity.setParentAccount(accountRepository.findById(account.getParentAccount().getAccountId()));
        accountRepository.persist(entity);
        return accountMapper.toDomain(entity);
    }

}