package com.propcool.polyclinic.config.interceptors;

import com.propcool.polyclinic.exceptions.NotAuthorizationException;
import com.propcool.polyclinic.models.Role;
import com.propcool.polyclinic.utils.security.SecurityUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Перехватчик http запроса с проверкой авторизации
 * */
@RequiredArgsConstructor
public class AuthorizationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(securityUtil.notAuthorization(role)) {
            throw new NotAuthorizationException();
        }
        return true;
    }
    private final SecurityUtil securityUtil;
    private final Role role;
}
