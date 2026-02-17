package com.microservices.auth_service.dto.registeruser.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {

        if (password == null) {
            context.disableDefaultConstraintViolation(); // disable default message

            context.buildConstraintViolationWithTemplate(
                    "Password should not be null"
            ).addConstraintViolation(); // custom message

            return false;
        }

        // EXAMPLE RULES (you can modify):
        boolean hasUpper = password.chars().anyMatch(Character::isUpperCase);
        boolean hasLower = password.chars().anyMatch(Character::isLowerCase);
        boolean hasDigit = password.chars().anyMatch(Character::isDigit);
        boolean hasSpecial = password.matches(".*[!@#$%^&*()_+=<>?/{}~].*");
        boolean minLength = password.length() >= 8;

        return hasUpper && hasLower && hasDigit && hasSpecial && minLength;
    }
}
