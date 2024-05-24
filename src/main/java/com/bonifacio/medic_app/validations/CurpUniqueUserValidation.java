package com.bonifacio.medic_app.validations;

import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class CurpUniqueUserValidation implements ConstraintValidator<CurpUniqueUser,String> {

    @Autowired
    private final IUserRepository userRepository;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.userRepository.existsByCurp(value);
    }
}
