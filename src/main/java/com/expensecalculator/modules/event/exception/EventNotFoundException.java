package com.expensecalculator.modules.event.exception;


public class EventNotFoundException extends RuntimeException{

    public EventNotFoundException() {
        super(String.format("The event cannot be null"));
    }

    public EventNotFoundException(Long id) {
        super(String.format("The event with ID %d was not found", id));
    }
}
