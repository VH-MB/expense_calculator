package com.expensecalculator.security.validation;

import com.expensecalculator.security.user.UserQueryRepository;
import com.expensecalculator.security.annotations.UniqueEmail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {

        if (userQueryRepository == null) {
            return true;
        }
        return userQueryRepository.findByEmail(email).equals(Optional.empty());
    }
}
