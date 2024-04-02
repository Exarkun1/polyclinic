package com.propcool.polyclinic.utils.security;

import com.propcool.polyclinic.dto.LoginRequest;
import com.propcool.polyclinic.models.Role;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Класс для авторизации
 * */
@Component
public class SecurityUtil {
    /**
     * Проверка авторизованности пользователя
     * */
    public boolean authorization(Role role, HttpSession session) {
        Object obj = session.getAttribute("user_details");
        return obj instanceof UserDetails auth && auth.role() == role;
    }

    /**
     * Проверка авторизованности текущего пользователя
     * */
    public boolean authorization(Role role) {
        return authorization(role, getSession());
    }
    /**
     * Проверка не авторизованности текущего пользователя
     * */
    public boolean notAuthorization(Role role) {
        return !authorization(role);
    }

    /**
     * Проверка аутентифицированности (не используется)
     * */
    public boolean authentication(HttpSession session) {
        Object obj = session.getAttribute("user_details");
        return obj instanceof UserDetails;
    }

    /**
     * Выдать атрибуты пользователя из сессии
     * */
    public UserDetails getDetails(HttpSession session) {
        Object obj = session.getAttribute("user_details");
        return obj instanceof UserDetails auth ? auth : null;
    }

    /**
     * Выдать атрибуты текущего пользователя из сессии
     * */
    public UserDetails getDetails() {
        return getDetails(getSession());
    }

    /**
     * Добавить/заменить атрибуты пользователя в сессию
     * */
    public void putDetails(LoginRequest userData, HttpSession session) {
        session.setAttribute("user_details", new UserDetails(
                userData.getLogin(),
                userData.getRole()
        ));
    }

    /**
     * Добавить/заменить атрибуты текущего пользователя в сессию
     * */
    public void putDetails(LoginRequest userData) {
        putDetails(userData, getSession());
    }

    /**
     * Добавить/заменить атрибуты текущего пользователя в сессию
     * */
    public void putDetails(String login, Role role) {
        getSession().setAttribute("user_details", new UserDetails(login, role));
    }

    /**
     * Удалить атрибуты пользователя из сессии
     * */
    public void removeDetails(HttpSession session) {
        session.removeAttribute("user_details");
    }

    /**
     * Удалить атрибуты текущего пользователя из сессии
     * */
    public void removeDetails() {
        removeDetails(getSession());
    }

    /**
     * Выдать сессию текущего пользователя
     * */
    public HttpSession getSession() {
        RequestAttributes requestAttributes = RequestContextHolder.currentRequestAttributes();
        ServletRequestAttributes attributes = (ServletRequestAttributes) requestAttributes;
        return attributes.getRequest().getSession(true);
    }
}
