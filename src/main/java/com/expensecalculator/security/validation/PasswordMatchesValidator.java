package com.expensecalculator.security.validation;

import com.expensecalculator.security.annotations.PasswordMatches;
import com.expensecalculator.security.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, String> {

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest signupRequest = new SignupRequest();

        return signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
    }

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}
