package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.dto.RegisterRequest;
import com.propcool.polyclinic.validation.annotations.ConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, RegisterRequest> {
    @Override
    public boolean isValid(RegisterRequest registerRequest, ConstraintValidatorContext constraintValidatorContext) {
        return registerRequest.getPassword() != null
                && registerRequest.getPassword().equals(registerRequest.getConfirmPassword());
    }
}
