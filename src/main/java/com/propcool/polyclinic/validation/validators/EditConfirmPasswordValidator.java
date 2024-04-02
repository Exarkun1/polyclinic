package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.dto.EditPasswordRequest;
import com.propcool.polyclinic.validation.annotations.ConfirmPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EditConfirmPasswordValidator implements ConstraintValidator<ConfirmPassword, EditPasswordRequest> {
    @Override
    public boolean isValid(EditPasswordRequest editRequest, ConstraintValidatorContext constraintValidatorContext) {
        return editRequest.getNewPassword() != null
                && editRequest.getNewPassword().equals(editRequest.getConfirmPassword());
    }
}
