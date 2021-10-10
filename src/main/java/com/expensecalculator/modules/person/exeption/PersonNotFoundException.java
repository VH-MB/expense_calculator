package com.expensecalculator.modules.person.exeption;


public class PersonNotFoundException extends RuntimeException {

    public PersonNotFoundException(Long id) {
        super(String.format("The person with ID %d was not found", id));
    }

}
