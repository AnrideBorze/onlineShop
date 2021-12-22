package com.sarakhman.JDBS;

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

    public List<User> getAll() {
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

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception with getAll");
        }
        return new ArrayList<>();
    }

    public void addUser(User user) {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getSalt());
            preparedStatement.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception with addUser");
        }
    }

    private void createNewTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_USERS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception with createNewTable");
        }
    }

    private boolean tableExist() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "users", null);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception with tableExist");
        }
        return false;

    }
}
