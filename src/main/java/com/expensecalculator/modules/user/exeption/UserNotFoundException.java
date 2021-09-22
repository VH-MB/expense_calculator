package com.expensecalculator.modules.user.exeption;


public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Long id) {
        super(String.format("The user with ID %d was not found", id));
    }

    public UserNotFoundException(String message) {
        super(String.format("Username not found with username: %s", message));
    }
}
