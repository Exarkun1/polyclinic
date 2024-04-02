package com.propcool.polyclinic.validation.validators;

import com.propcool.polyclinic.utils.security.SecurityUtil;
import com.propcool.polyclinic.utils.security.UserDetails;
import com.propcool.polyclinic.services.UserDataService;
import com.propcool.polyclinic.validation.annotations.UniqueLogin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueLoginValidator implements ConstraintValidator<UniqueLogin, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        UserDetails userDetails = securityUtil.getDetails();
        if(userDetails != null && userDetails.login().equals(s)) {
            return true;
        }
        return !userDataService.containsUserData(s);
    }
    private final UserDataService userDataService;
    private final SecurityUtil securityUtil;
}
