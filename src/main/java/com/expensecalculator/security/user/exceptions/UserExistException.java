package com.expensecalculator.security.user.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserExistException extends RuntimeException {

    public UserExistException(String username) {
        super(String.format("The User %s already exist. Please check credentials", username));
    }
}
