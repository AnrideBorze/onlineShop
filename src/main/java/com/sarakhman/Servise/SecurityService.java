package com.sarakhman.Servise;

import com.sarakhman.CookieHandler;
import com.sarakhman.Entity.User;
import com.sarakhman.JDBS.JDBSUserDao;
import org.apache.commons.codec.digest.DigestUtils;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityService {

    JDBSUserDao jdbsUserDao;
    Map<String, String> cookieMap = new HashMap<>();


    public SecurityService(JDBSUserDao jdbsUserDao) {
        this.jdbsUserDao = jdbsUserDao;
    }

    public void logIn(User currentUser) {
        List<User> allUsers = jdbsUserDao.getAll();
        for (User userToLogIn : allUsers) {
            if (currentUser.getEmail().equals(userToLogIn.getEmail())) {
                if (isPasswordCorrect(currentUser, userToLogIn)) {
                    creatingUserToken();
                }
            }
        }

    }

    public void registration(User currentUser) {
        List<User> allUsers = jdbsUserDao.getAll();
        for (User userFromDB : allUsers) {
            if (!userFromDB.getName().equals(currentUser.getName())) {
                currentUser.setPassword(addSaltAndHash(currentUser));
                jdbsUserDao.addUser(currentUser);
            }
        }

    }

    public void creatingUserToken() {
        CookieHandler cookieHandler = new CookieHandler();
        String cookie = cookieHandler.getTokenValue();
        cookieMap.put("UserToken", cookie);
    }

    private boolean isPasswordCorrect(User currentUser, User userToLogIn) {

        if (hashMd5(currentUser.getPassword() + userToLogIn.getSalt()).equals(userToLogIn.getPassword())) {
            return true;
        }
        return false;
    }

    private String addSaltAndHash(User currentUser) {
        String password = currentUser.getPassword();
        String salt = addSalt(currentUser);
        String hashPassword = hashMd5(password, salt);
        return hashPassword;

    }

    private String addSalt(User currentUser) {
        String salt = currentUser.getName() + currentUser.getPassword() + currentUser.getEmail();
        return salt;
    }

    private String hashMd5(String password) {
        String hashPassword = DigestUtils.md5Hex(password);
        return hashPassword;

    }

    private String hashMd5(String password, String salt) {
        String hashPassword = DigestUtils.md5Hex(password + salt);
        return hashPassword;
    }

}
