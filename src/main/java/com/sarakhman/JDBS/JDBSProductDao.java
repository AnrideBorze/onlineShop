package com.sarakhman.JDBS;

import com.sarakhman.Entity.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBSProductDao {

    private static final String ADD_PRODUCT = "ADD into products (name, price, date) VALUES (?,?,?);";
    private static final String SHOW_ALL = "SELECT id, name, price, date FROM products";
    private static final String UPDATE_PRODUCT = "UPDATE products SET name = ?, price = ?, date = ? WHERE id =?";
    private static final String DELETE = "DELETE from products WHERE id = ?;";


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/online-shop",
                "admin", "qwerty");
    }


    public void addProduct(Product product) throws SQLException{
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getCreationDate()));
            preparedStatement.executeUpdate();
        }
    }

    public List<Product> showAll() throws SQLException{
        try(Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SHOW_ALL);
            ResultSet resultSet = preparedStatement.getResultSet())
        {
            List<Product> allProducts = new ArrayList<>();
            while (resultSet.next()){
                Product currentProduct = new Product(resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getDouble("price"),
                        resultSet.getTimestamp("date").toLocalDateTime());

                allProducts.add(currentProduct);
            }
            return allProducts;
        }
    }

    public void updateProduct(Product product, int id) throws SQLException{
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(product.getCreationDate()));
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException{
        try(Connection connection = getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
        }
    }
}
