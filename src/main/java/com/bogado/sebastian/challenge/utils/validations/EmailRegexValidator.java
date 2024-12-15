package com.bogado.sebastian.challenge.utils.validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailRegexValidator implements ConstraintValidator<EmailConfigurableRegex, String> {
    @Value("${user.eamil.regex}")
    private String regex;

    private Pattern pattern;

    public EmailRegexValidator() {
        // as default
        this.regex="^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.cl$";
    }

    @Override
    public void initialize(EmailConfigurableRegex constraintAnnotation) {
        pattern = Pattern.compile(regex);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }
        return pattern.matcher(value).matches();
    }
}