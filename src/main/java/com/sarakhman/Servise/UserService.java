package com.sarakhman.Servise;

import com.sarakhman.Entity.User;
import com.sarakhman.JDBS.JDBSUserDao;

import java.util.List;

public class UserService {
    JDBSUserDao jdbsUserDao = new JDBSUserDao();

    public UserService(JDBSUserDao jdbsUserDao) {
        this.jdbsUserDao = jdbsUserDao;
    }

    public void addUser(User user){
        jdbsUserDao.addUser(user);
    }

    public List<User> getAllUsers(){
        List<User> allUsers = jdbsUserDao.getAll();
        return allUsers;
    }
}
