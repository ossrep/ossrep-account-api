package com.ossrep.contact;

import com.ossrep.account.AccountEntity;
import com.ossrep.contact_type.ContactType;
import com.ossrep.contact_type.ContactTypeEntity;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Contact {

    private Integer contactId;

    @NotNull
    private ContactType contactType;

    @NotNull
    private AccountEntity account;

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