package com.propcool.polyclinic.services;

import com.propcool.polyclinic.dto.EditPasswordRequest;
import com.propcool.polyclinic.models.UserData;
import com.propcool.polyclinic.repositories.UserDataRepository;
import com.propcool.polyclinic.utils.security.CryptoUtil;
import com.propcool.polyclinic.utils.security.UserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Сервис для авторизации
 * */
@Service
@Transactional
@RequiredArgsConstructor
public class UserDataService {
    /**
     * Выдать данные авторизации по логину
     * */
    @Transactional(readOnly = true)
    public UserData getUserData(String login) {
        return userDataRepository.findByLogin(login).orElse(null);
    }

    /**
     * Выдать данные авторизации текущего пользователя
     * */
    @Transactional(readOnly = true)
    public UserData getUserData(UserDetails userDetails) {
        return getUserData(userDetails.login());
    }

    /**
     * Проверить существование пользователя
     * */
    @Transactional(readOnly = true)
    public boolean containsUserData(String login) {
        return getUserData(login) != null;
    }

    /**
     * Изменить пароль текущего пользователя-пациента
     * */
    public void editPassword(EditPasswordRequest editPasswordRequest, UserDetails details) {
        UserData userData = getUserData(details.login());
        userData.setPassword(cryptoUtil.encode(editPasswordRequest.getNewPassword()));
    }

    private final UserDataRepository userDataRepository;
    private final CryptoUtil cryptoUtil;
}
