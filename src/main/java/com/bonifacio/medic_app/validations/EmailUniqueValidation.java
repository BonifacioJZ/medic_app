package com.bonifacio.medic_app.validations;


import com.bonifacio.medic_app.persitence.repositories.IUserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class EmailUniqueValidation implements ConstraintValidator<EmailUnique,String> {

    @Autowired
    private final IUserRepository repository;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !this.repository.existsByEmail(value);
    }
}
