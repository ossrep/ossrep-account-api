package com.ossrep.account;

import com.ossrep.account_type.AccountType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Account {

    private Integer accountId;

    @NotNull
    private AccountType accountType;

    private Account parentAccount;

}