package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDAOMariaDB implements OrderDAO {
    private final DAOFactory daoFactory;

    public OrderDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;

        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "SELECT * FROM "
                )
        ){

        }
    }

    @Override
    public ArrayList<Order> getUserOrders(String userEmail) {
        return null;
    }

    @Override
    public int createOrder(Order order) {
        try (
                Connection connection = daoFactory.getConnection();
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO orders(customer_id, date, state, shippingFees, discountCode)" +
                                "VALUES (?, CURRENT_TIME, 1, ?, ?)",
                        Statement.RETURN_GENERATED_KEYS
                );
        ) {
            stmt.setString(1, order.getCustomerId());
            stmt.setInt(2, order.getShippingFees());
            stmt.setString(3, order.getDiscountCode());

            if (stmt.executeUpdate() == 0)
                throw new SQLException("Order creation failed");

            int orderId = 0;
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next())
                orderId = rs.getInt(1);

            if (orderId == 0)
                throw new SQLException("null order id, so order creation failed");

            insertRows(orderId, order.getContent());
            return orderId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void insertRows(int orderId, HashMap<Integer, Integer> products) {
        String sql = "INSERT INTO content_orders(order_id, product_id, quantity) VALUES ";

        for(Map.Entry<Integer, Integer> entry : products.entrySet()) {
            sql += "(" + orderId + "," + entry.getKey() + "," + entry.getValue() + "),";;
        }

        sql = sql.substring(0, sql.length() - 1);

        try (
            Connection connection = daoFactory.getConnection();
            Statement stmt = connection.createStatement();
        ) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
