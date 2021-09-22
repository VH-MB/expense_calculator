package com.expensecalculator.modules.user.dto;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class UserDto {

    @Id
    private Long id;
    @NotBlank
    @Size(min = 2, max = 30)
    private String firstName;
    private String lastName;
}
