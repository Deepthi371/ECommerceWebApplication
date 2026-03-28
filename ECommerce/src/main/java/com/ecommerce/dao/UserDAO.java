package com.ecommerce.dao;

import com.ecommerce.model.User;

import java.sql.*;

public class UserDAO {
    private final String jdbcURL = "jdbc:mysql://localhost:3306/ecommerce2";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "Deepu";

    private static final String INSERT_USER_SQL = 
        "INSERT INTO users (name, email, phone, address, password) VALUES (?, ?, ?, ?, ?)";

    public boolean saveUser(User user) throws ClassNotFoundException {
        boolean rowInserted = false;
        
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
             PreparedStatement statement = connection.prepareStatement(INSERT_USER_SQL)) {

            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPhone());
            statement.setString(4, user.getAddress());
            statement.setString(5, user.getPassword());  // In production, hash this!

            rowInserted = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rowInserted;
    }
}

