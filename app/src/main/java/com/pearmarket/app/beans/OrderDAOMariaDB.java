package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Order;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.beans.elements.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

public class OrderDAOMariaDB implements OrderDAO {
    private final DAOFactory daoFactory;

    public OrderDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public Order getOrder(int orderId) {
        Order order = null;

        // TODO : try to replace by one statement or use userDao (which is better ?)
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT orders.id, orders.date, orders.discountCode, " +
                            "       orders.shippingFees, orders.state, " +
                            "       users.address, users.email, users.phone, " +
                            "       users.name, users.firstname " +
                            "FROM orders" +
                            "    INNER JOIN users ON orders.customer_id = users.email " +
                            "WHERE id=?"
            );

            stmt.setInt(1, orderId);
            ResultSet result = stmt.executeQuery();

            if (result.first()) {
                order = new Order();
                order.setId(orderId);
                order.setDate(result.getTimestamp("date"));
                order.setDiscountCode(result.getString("discountCode"));
                order.setShippingFees(result.getInt("shippingFees"));
                order.setState(result.getInt("state"));

                order.setCustomer(fillUser(result));
                order.setContent(getOrderRows(orderId));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return order;
    }

    private LinkedHashMap<Product, Integer> getOrderRows(int orderId) {
        LinkedHashMap<Product, Integer> products = new LinkedHashMap<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt2 = connection.prepareStatement(
                    "SELECT co.*, p.name, p.price " +
                            "FROM content_orders co " +
                            "   INNER JOIN products p on co.product_id = p.id " +
                            "WHERE co.order_id = ?"
            );
            stmt2.setInt(1, orderId);
            ResultSet res = stmt2.executeQuery();

            while (res.next()) {
                Product productRow = new Product();
                productRow.setPrice(res.getInt("price"));
                productRow.setName(res.getString("name"));

                products.put(productRow, res.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return products;
    }

    @Override
    public ArrayList<Order> getUserOrders(String userEmail) {
        ArrayList<Order> orders = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT orders.id, orders.date, orders.discountCode, " +
                            "       orders.shippingFees, orders.state, " +
                            "       users.address, users.email, users.phone, " +
                            "       users.name, users.firstname " +
                            "FROM orders" +
                            "    INNER JOIN users ON orders.customer_id = users.email " +
                            "WHERE users.email=?"
            );

            stmt.setString(1, userEmail);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Order order = fillOrder(result);

                order.setCustomer(fillUser(result));
                order.setContent(getOrderRows(order.getId()));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return orders;
    }

    private User fillUser(ResultSet result) throws SQLException {
        User user = new User();
        user.setEmail(result.getString("email"));
        user.setAddress(result.getString("address"));
        user.setPhone(result.getString("phone"));
        user.setName(result.getString("name"));
        user.setFirstname(result.getString("firstname"));

        return user;
    }

    private Order fillOrder(ResultSet result) throws SQLException {
        Order order = new Order();
        order.setId(result.getInt("id"));
        order.setDate(result.getTimestamp("date"));
        order.setDiscountCode(result.getString("discountCode"));
        order.setShippingFees(result.getInt("shippingFees"));
        order.setState(result.getInt("state"));
        return order;
    }

    @Override
    public ArrayList<Order> getOrders() {
        ArrayList<Order> orders = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT orders.id, orders.date, orders.discountCode, orders.shippingFees, orders.state, " +
                            "       users.address, users.email, users.phone, " +
                            "       users.name, users.firstname " +
                            "FROM orders" +
                            "    INNER JOIN users ON orders.customer_id = users.email "
            );

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Order order = fillOrder(result);

                order.setCustomer(fillUser(result));
                order.setContent(getOrderRows(order.getId()));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return orders;
    }

    @Override
    public int createOrder(Order order) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO orders(customer_id, date, state, shippingFees, discountCode)" +
                            "VALUES (?, CURRENT_TIME, 1, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            stmt.setString(1, order.getCustomer().getEmail());
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
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return -1;
    }

    private void insertRows(int orderId, LinkedHashMap<Product, Integer> products) {
        // Probablement pas ouf niveau DAO...
        ProductDAO productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);

        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO content_orders(order_id, product_id, quantity) VALUES ");
        for(Map.Entry<Product, Integer> entry : products.entrySet()) {
            sqlBuilder.append("(").append(orderId).append(",").append(entry.getKey().getId()).append(",").append(entry.getValue()).append("),");

            productDAO.decrementQuantity(entry.getKey().getId(), entry.getValue());
        }
        String sql = sqlBuilder.toString();

        sql = sql.substring(0, sql.length() - 1);

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            Statement stmt = connection.createStatement();

            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public boolean updateState(int id, int state) {
        boolean succeeded = false;
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE orders SET state=? WHERE id=?;"
            );
            stmt.setInt(1, state);
            stmt.setInt(2, id);

            if (stmt.executeUpdate() == 1)
                succeeded = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
        return succeeded;
    }
}
