package com.expensecalculator.security.user.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String message) {
        super(String.format("Username not found with username: %s", message));
    }
}
