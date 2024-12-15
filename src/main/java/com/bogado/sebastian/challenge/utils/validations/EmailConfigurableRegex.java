package com.bogado.sebastian.challenge.utils.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = EmailRegexValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailConfigurableRegex {
    String message() default "El email no cumple con el patron requerido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}