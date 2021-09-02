package com.rislah.tpilet.dto;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Setter
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = CompanyDtoWithBuses.class, name = "withBuses"),
//})
public class CompanyDto {
    private int id;

    @NotBlank
    @Length(min = 4, max = 100)
    private String name;

    @Email
    @NotNull
    private String email;

    private int phone;
}
