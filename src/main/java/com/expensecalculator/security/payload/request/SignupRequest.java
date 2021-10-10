package com.expensecalculator.security.payload.request;

import com.expensecalculator.shared.validation.ValidationMessages;
import com.expensecalculator.security.annotations.UniqueEmail;
import com.expensecalculator.security.annotations.PasswordMatches;
import com.expensecalculator.security.annotations.UniqueUsername;
import com.expensecalculator.security.annotations.ValidEmail;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    @Email(message = ValidationMessages.EMAIL_ERROR)
    @ValidEmail
    @UniqueEmail
    private String email;

    @NotBlank(message = ValidationMessages.FIRST_NAME_NOT_BLANK)
    private String firstName;

    @NotBlank(message = ValidationMessages.LAST_NAME_NOT_BLANK)
    private String lastName;

    @NotBlank(message = ValidationMessages.USERNAME_NOT_BLANK)
    @UniqueUsername
    private String username;

    @NotBlank(message = ValidationMessages.PASSWORD_NOT_BLANK)
    @Size(min = 6)
    private String password;

    private String confirmPassword;
}
