package com.expensecalculator.shared.validation;

import java.io.Serializable;

public class ValidationMessages implements Serializable {

    public static final String FIRST_NAME_NOT_BLANK= "Please enter your name";
    public static final String LAST_NAME_NOT_BLANK = "Please enter your lastname";
    public static final String USERNAME_NOT_BLANK = "Please enter your username";
    public static final String PASSWORD_NOT_BLANK = "Password is required";
    public static final String DESCRIPTION_NOT_EMPTY = "Please enter your description";
    public static final String EVENT_NAME_REQUIRED = "Please enter a name for the event";
    public static final String FORMAT_DATE = "yyyy-mm-dd HH:mm:ss";
    public static final String EMAIL_ERROR = "It should have email format";
}
