package com.sarakhman.JDBS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBSUserDao {
    private static final String SHOW_ALL = "SELECT id, email, pass, sole FROM users;";
    private static final String CREATE_TABLE = "CREATE TABLE users (id SERIAL, email varchar(255), pass varchar(255), sole varchar(255));";
    private static final String ADD_USER = "ADD INTO users (email, pass, sole) VALUES (?, ?, ?);";

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "admin", "qwerty");
    }


}
