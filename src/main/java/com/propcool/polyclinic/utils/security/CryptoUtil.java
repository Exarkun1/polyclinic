package com.propcool.polyclinic.utils.security;

import org.springframework.stereotype.Component;

/**
 * Класс для кодирования пароля (в разработке)
 * */
@Component
public class CryptoUtil {
    public String encode(String password) {
        return password;
    }
}
