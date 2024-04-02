package com.propcool.polyclinic.converters;

import com.propcool.polyclinic.dto.RegisterRequest;
import com.propcool.polyclinic.models.Patient;
import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.models.UserData;
import com.propcool.polyclinic.utils.security.CryptoUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegisterRequestToPatientConverter implements Converter<RegisterRequest, Patient> {
    @Override
    public Patient convert(RegisterRequest source) {
        Patient patient = new Patient();
        patient.setFullName(source.getFullName());
        patient.setBirthday(source.getBirthday());
        patient.setAddress(source.getAddress());
        patient.setUserData(new UserData(
                source.getLogin(),
                cryptoUtil.encode(source.getPassword()),
                Role.Patient
        ));
        return patient;
    }
    private final CryptoUtil cryptoUtil;
}
