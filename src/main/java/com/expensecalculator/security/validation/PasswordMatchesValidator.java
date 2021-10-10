package com.expensecalculator.security.validation;

import com.expensecalculator.security.annotations.PasswordMatches;
import com.expensecalculator.security.payload.request.SignupRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext constraintValidatorContext) {
        SignupRequest signupRequest = (SignupRequest) obj;

        return signupRequest.getPassword().equals(signupRequest.getConfirmPassword());
    }
}
