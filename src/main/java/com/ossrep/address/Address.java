package com.ossrep.address;

import com.ossrep.account.Account;
import com.ossrep.address_type.AddressType;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class Address {

    private Integer addressId;

    @NotNull
    private AddressType addressType;

    @NotNull
    private Account account;

    @NotEmpty
    private String addressLine1;

    private String addressLine2;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zipCode;

}