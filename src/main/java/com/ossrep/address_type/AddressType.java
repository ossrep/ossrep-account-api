package com.ossrep.address_type;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class AddressType {

    private Integer addressTypeId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

}