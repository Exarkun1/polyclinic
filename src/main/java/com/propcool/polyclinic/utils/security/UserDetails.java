package com.propcool.polyclinic.utils.security;

import com.propcool.polyclinic.models.Role;

/**
 * Атрибуты пользователя, хранящиеся в сессии
 * */
public record UserDetails(
        String login,
        Role role
) {}
