package com.ossrep.contact;

import com.ossrep.account.Account;
import com.ossrep.contact_type.ContactType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Contact {

    private Integer contactId;

    @NotNull
    private ContactType contactType;

    @NotNull
    private Account account;

    @NotEmpty
    private String firstName;

    private String middleName;

    @NotEmpty
    private String lastName;

    private String suffix;

    @Email
    private String email;

    private String phone;

}