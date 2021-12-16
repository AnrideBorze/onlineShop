package com.sarakhman;

import com.sarakhman.JDBS.JDBSUserDao;
import com.sarakhman.JDBS.JDBSProductDao;

import java.sql.*;

public class Starter {
    JDBSUserDao jdbsUserDao = new JDBSUserDao();
    JDBSProductDao jdbsProductDao = new JDBSProductDao();


    private static final String URLUsers = "jdbc:sqlite:users.db";
    private static final String URLProduct = "jdbc:sqlite:product.db";

    private static final String CREATE_TABLE_USERS = """
               CREATE TABLE Users(
               ID INT PRIMARY KEY     NOT NULL,
               NAME           TEXT    NOT NULL,
               PASSWORD        CHAR(50) NOT NULL,
               SOLE         REAL NOT NULL
            )
                    """;

    private static final String CREATE_TABLE_PRODUCTS = """
               CREATE TABLE Users(
               ID INT PRIMARY KEY     NOT NULL,
               NAME           TEXT    NOT NULL,
               PRICE        CHAR(50) NOT NULL,
               CREATION_DATA         REAL NOT NULL
            )
                    """;

    public static void main(String[] args) {
        //createNewDatabase();
    }
git stat
    public static void createNewTable() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URLUsers)){
            Statement statement = conn.createStatement();
            statement.execute(CREATE_TABLE_USERS);
        }


    }


    public static void createNewDatabase() throws SQLException {


        try (Connection conn = DriverManager.getConnection(URLUsers)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
    }
}
}
