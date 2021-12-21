package com.sarakhman;

import com.sarakhman.JDBS.JDBSUserDao;
import com.sarakhman.JDBS.JDBSProductDao;

import java.sql.*;

public class Starter {
    JDBSUserDao jdbsUserDao = new JDBSUserDao();
    JDBSProductDao jdbsProductDao = new JDBSProductDao();


    private static final String URLUsers = "jdbc:sqlite:users.db";
    private static final String URLProduct = "jdbc:sqlite:product.db";





    private static final String CREATE_TABLE_PRODUCTS = """
               CREATE TABLE Users(
               id INT PRIMARY KEY       NOT NULL,
               name                     VARCHAR(255)    NOT NULL,
               price                    NUMERIC(50) NOT NULL,
               creation_data            DATE NOT NULL
            )
                    """;

    public static void main(String[] args) throws SQLException {
        //createNewDatabase();
        //createNewTable();
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
