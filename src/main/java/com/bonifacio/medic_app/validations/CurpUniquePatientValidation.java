package com.bonifacio.medic_app.validations;

import com.bonifacio.medic_app.persitence.repositories.IPatientRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CurpUniquePatientValidation implements ConstraintValidator<CurpUniquePatient,String> {
    @Autowired
    private final IPatientRepository patientRepository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.patientRepository.existsByCurp(value);
    }
}
