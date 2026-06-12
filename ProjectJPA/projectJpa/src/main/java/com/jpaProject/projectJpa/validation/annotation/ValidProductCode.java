package com.jpaProject.projectJpa.validation.annotation;

import com.jpaProject.projectJpa.validation.validator.ProductCodeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)

@Retention(RetentionPolicy.RUNTIME)

@Constraint(
        validatedBy =
                ProductCodeValidator.class
)

public @interface ValidProductCode {

    String message()
            default
            "Invalid Product Code";

    Class<?>[] groups()
            default {};

    Class<? extends Payload>[] payload()
            default {};
}