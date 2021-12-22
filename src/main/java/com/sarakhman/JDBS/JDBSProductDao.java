package com.sarakhman.JDBS;

import com.sarakhman.Entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBSProductDao {

    private static final String CREATE_TABLE_PRODUCTS = """
               CREATE TABLE products(
               id INT PRIMARY KEY       NOT NULL,
               name                     VARCHAR(255)    NOT NULL,
               price                    NUMERIC(50) NOT NULL,
               creation_data            DATE NOT NULL
            )
                    """;

    private static final String ADD_PRODUCT = "ADD into products (name, price, date) VALUES (?,?,?);";
    private static final String SHOW_ALL = "SELECT id, name, price, date FROM products";
    private static final String UPDATE_PRODUCT = "UPDATE products SET name = ?, price = ?, date = ? WHERE id =?";
    private static final String DELETE = "DELETE from products WHERE id = ?;";


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "admin", "qwerty");
    }


    public void addProduct(Product product) {
        if (!tableExist()) {
            createNewTable();
        }
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getCreationDate()));
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in add Product");
        }
    }

    public List<Product> getAllProducts() {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL);
             ResultSet resultSet = preparedStatement.getResultSet()) {
            List<Product> allProducts = new ArrayList<>();
            while (resultSet.next()) {
                Product currentProduct = new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getTimestamp("date").toLocalDateTime());

                allProducts.add(currentProduct);
            }
            return allProducts;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in showAll Product");
        }
        return new ArrayList<>();

    }

    public void updateProduct(Product product, int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getCreationDate()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in updateProduct");
        }
    }

    public void delete(int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in delete");
        }
    }

    private void createNewTable() {
        try (Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_TABLE_PRODUCTS);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in createNewTable Product");
        }
    }

    private boolean tableExist() {
        try (Connection connection = getConnection()) {
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            ResultSet resultSet = databaseMetaData.getTables(null, null, "products", null);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Exception in tableExist Product");
        }
        return false;

    }
}
