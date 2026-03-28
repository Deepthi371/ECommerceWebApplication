package com.ecommerce.controller;

import java.sql.*;
import java.util.*;
import com.ecommerce.model.CartItem;


public class OrderDAO {

    private static Statement DBConnection;

	public static int saveOrder(String customerName, String email, List<CartItem> cart, double totalAmount) {
        int orderId = -1;

        String insertOrder = "INSERT INTO orders (customer_name, email, total_amount, order_date) VALUES (?, ?, ?, NOW())";
        String insertItem = "INSERT INTO order_items (order_id, product_name, brand, price, quantity) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psOrder = conn.prepareStatement(insertOrder, Statement.RETURN_GENERATED_KEYS);
             PreparedStatement psItem = conn.prepareStatement(insertItem)) {

            conn.setAutoCommit(false);

            // Insert into orders
            psOrder.setString(1, customerName);
            psOrder.setString(2, email);
            psOrder.setDouble(3, totalAmount);
            psOrder.executeUpdate();

            // Get generated order ID
            ResultSet rs = psOrder.getGeneratedKeys();
            if (rs.next()) {
                orderId = rs.getInt(1);
            }

            // Insert order items
            for (CartItem item : cart) {
                psItem.setInt(1, orderId);
                psItem.setString(2, item.getName());
                psItem.setString(3, item.getBrand());
                psItem.setDouble(4, item.getPrice());
                psItem.setInt(5, item.getQuantity());
                psItem.addBatch();
            }
            psItem.executeBatch();

            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return orderId;
    }
}
