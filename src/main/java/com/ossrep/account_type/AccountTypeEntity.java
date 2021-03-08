package com.ossrep.account_type;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "AccountType")
@Table(name = "account_type")
@Data
public class AccountTypeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_type_id")
    private Integer accountTypeId;

    @Column(name = "name")
    @NotEmpty
    private String name;

    @Column(name = "description")
    @NotEmpty
    private String description;

}