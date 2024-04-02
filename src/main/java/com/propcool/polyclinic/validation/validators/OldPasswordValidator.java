package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.models.UserData;
import com.propcool.polyclinic.utils.security.CryptoUtil;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.services.UserDataService;
import com.propcool.polyclinic.validation.annotations.OldPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OldPasswordValidator implements ConstraintValidator<OldPassword, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        UserData userData = userDataService.getUserData(securityUtil.getDetails());
        return userData != null && userData.getPassword().equals(cryptoUtil.encode(s));
    }
    private final UserDataService userDataService;
    private final SecurityUtil securityUtil;
    private final CryptoUtil cryptoUtil;
}
