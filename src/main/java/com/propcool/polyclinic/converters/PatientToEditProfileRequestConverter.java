package com.propcool.polyclinic.converters;

import com.propcool.polyclinic.dto.EditProfileRequest;
import com.propcool.polyclinic.models.Patient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PatientToEditProfileRequestConverter implements Converter<Patient, EditProfileRequest> {
    @Override
    public EditProfileRequest convert(Patient source) {
        EditProfileRequest editRequest = new EditProfileRequest();
        editRequest.setLogin(source.getUserData().getLogin());
        editRequest.setFullName(source.getFullName());
        editRequest.setBirthday(source.getBirthday());
        editRequest.setAddress(source.getAddress());
        return editRequest;
    }
}
