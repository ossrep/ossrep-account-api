package com.ossrep.address;

import com.ossrep.account.AccountEntity;
import com.ossrep.address_type.AddressTypeEntity;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity(name = "Address")
@Table(name = "address")
@Data
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "address_id")
    private Integer addressId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_type_id", nullable = false)
    @NotNull
    private AddressTypeEntity addressType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    @NotNull
    private AccountEntity account;

    @Column(name = "address_line_1")
    @NotEmpty
    private String addressLine1;

    @Column(name = "address_line_2")
    private String addressLine2;

    @Column(name = "city")
    @NotEmpty
    private String city;

    @Column(name = "state")
    @NotEmpty
    private String state;

    @Column(name = "zip_code")
    @NotEmpty
    private String zipCode;

}