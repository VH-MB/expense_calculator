package com.expensecalculator.security.user.dto;

import com.expensecalculator.shared.validation.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class UserDTO {

    private Long id;
    @NotBlank(message = ValidationMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;
    @NotBlank(message = ValidationMessages.LAST_NAME_NOT_BLANK)
    private String lastName;
    @NotBlank(message = ValidationMessages.USERNAME_NOT_BLANK)
    private String username;
}
