package com.bonifacio.medic_app.validations;

import com.bonifacio.medic_app.persitence.repositories.IFamiliarRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class CurpUniqueFamiliarValidation implements ConstraintValidator<CurpUniqueFamiliar,String> {

    @Autowired
    private final IFamiliarRepository familiarRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.familiarRepository.existsByCurp(value);
    }
}
