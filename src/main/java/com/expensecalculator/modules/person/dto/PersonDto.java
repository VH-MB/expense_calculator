package com.expensecalculator.modules.person.dto;

import com.expensecalculator.shared.validation.ValidationMessages;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
public class PersonDto {

    @Id
    private Long id;

    @NotBlank(message = ValidationMessages.FIRST_NAME_NOT_BLANK)
    @Size(min = 2, max = 30)
    private String firstName;

    @NotBlank(message = ValidationMessages.LAST_NAME_NOT_BLANK)
    @Size(min = 2, max = 30)
    private String lastName;
}
