package com.sarakhman.JDBS;

import com.sarakhman.Entity.Product;
import com.sarakhman.Entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBSUserDao {
    private static final String SHOW_ALL = "SELECT id, name, email, pass, salt FROM users;";
    private static final String ADD_USER = "ADD INTO users (name, email, pass, salt) VALUES (?, ?, ?, ?);";

    private static final String CREATE_TABLE_USERS = """
               CREATE TABLE Users(
               id INT PRIMARY KEY     NOT NULL,
               name                  VARCHAR(255) NOT NULL,
               email                 VARCHAR(255) NOT NULL,
               pass                  VARCHAR(255) NOT NULL,
               salt                  VARCHAR(255) NOT NULL
            )
                    """;

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "admin", "qwerty");
    }

    public List<User> showAll() throws SQLException {
        if (!tableExist()) {
            createNewTable();
        }

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL);
             ResultSet resultSet = preparedStatement.getResultSet()) {
            List<User> allUsers = new ArrayList<>();

            while (resultSet.next()) {
                User currentUser = new User(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("pass"),
                        resultSet.getString("salt"));
                allUsers.add(currentUser);
            }

            return allUsers;

        }

    }

    public void addUser(User user) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, "name");
            preparedStatement.setString(2, "email");
            preparedStatement.setString(3, "pass");
            preparedStatement.setString(4, "salt");
            preparedStatement.executeUpdate();


        }
    }

    private void createNewTable() throws SQLException {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USERS);
            preparedStatement.executeUpdate();
        }
    }

    private boolean tableExist() throws SQLException {
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "users", null);
            if (resultSet.next()) {
                return true;
            }
        }
        return false;

    }
}
