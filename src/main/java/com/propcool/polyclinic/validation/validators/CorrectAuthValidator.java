package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.dto.LoginRequest;
import com.propcool.polyclinic.models.UserData;
import com.propcool.polyclinic.utils.security.CryptoUtil;
import com.propcool.polyclinic.services.UserDataService;
import com.propcool.polyclinic.validation.annotations.CorrectAuth;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CorrectAuthValidator implements ConstraintValidator<CorrectAuth, LoginRequest> {
    @Override
    public boolean isValid(LoginRequest loginRequest, ConstraintValidatorContext constraintValidatorContext) {
        UserData userData = userDataService.getUserData(loginRequest.getLogin());
        return userData != null
                && cryptoUtil.encode(loginRequest.getPassword()).equals(userData.getPassword())
                && loginRequest.getRole() == userData.getRole();
    }
    private final UserDataService userDataService;
    private final CryptoUtil cryptoUtil;
}
