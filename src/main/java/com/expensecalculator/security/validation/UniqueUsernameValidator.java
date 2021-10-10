package com.expensecalculator.security.validation;

import com.expensecalculator.security.user.UserQueryRepository;
import com.expensecalculator.security.annotations.UniqueUsername;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Optional;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private UserQueryRepository userQueryRepository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {

        if (userQueryRepository == null) {
            return true;
        }

        return userQueryRepository.findByUsername(username).equals(Optional.empty());
    }
}
