package com.expensecalculator.security.payload.request;


import com.expensecalculator.shared.validation.ValidationMessages;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotBlank(message = ValidationMessages.USERNAME_NOT_BLANK)
    private String username;

    @NotBlank(message = ValidationMessages.PASSWORD_NOT_BLANK)
    private String password;
}
