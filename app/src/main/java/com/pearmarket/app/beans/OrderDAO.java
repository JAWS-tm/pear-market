package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Order;

import java.util.ArrayList;

public interface OrderDAO {
    Order getOrder(int orderId);
    ArrayList<Order> getUserOrders(String userEmail);
    ArrayList<Order> getOrders();
    int createOrder(Order order);
    boolean updateState(int id, int state);
}
