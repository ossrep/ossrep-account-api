package com.ossrep.contact_type;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class ContactType {

    private Integer contactTypeId;

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

}