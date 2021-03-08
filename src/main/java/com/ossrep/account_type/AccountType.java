package com.ossrep.account_type;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AccountType {

    private Integer accountTypeId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

}