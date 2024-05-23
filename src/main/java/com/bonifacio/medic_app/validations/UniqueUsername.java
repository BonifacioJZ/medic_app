package com.bonifacio.medic_app.validations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = {UsernameUniqueValidation.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {
    String message() default "El Nombre de usuaruio ya esta en uso";
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default {};
}
