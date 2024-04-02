package com.propcool.polyclinic.config.interceptors;

import com.propcool.polyclinic.annotations.HttpInterceptor;
import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Перехватчик http запроса с проверкой авторизации доктором
 * */
@HttpInterceptor(path = "/doctor/**")
public class DoctorInterceptor extends AuthorizationInterceptor {
    @Autowired
    public DoctorInterceptor(SecurityUtil securityUtil) {
        super(securityUtil, Role.Doctor);
    }
}
