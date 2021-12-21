package com.sarakhman.Servise;

import com.sarakhman.JDBS.JDBSUserDao;

public class SecurityService {
    JDBSUserDao jdbsUserDao = new JDBSUserDao();


    public SecurityService(JDBSUserDao jdbsUserDao) {
        this.jdbsUserDao = jdbsUserDao;
    }


}
