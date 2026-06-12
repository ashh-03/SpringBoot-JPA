package com.jpaProject.projectJpa.validation.validator;

import com.jpaProject.projectJpa.validation.annotation.ValidProductCode;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ProductCodeValidator implements ConstraintValidator
        <ValidProductCode,
                String> {

    @Override

    public boolean isValid(

            String value,

            ConstraintValidatorContext context
    ) {

        if(value == null)
        {
            return false;
        }

        return value.startsWith(
                "PRD-"
        );
    }
}
