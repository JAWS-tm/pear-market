package com.pearmarket.app.beans.elements;

import com.pearmarket.app.utils.Cart;

import java.sql.Date;
import java.util.HashMap;

public class Order {
    /**
     * content: id de produit en key et les quantité en value
     */
    private HashMap<Integer, Integer> content;
    private String customerId;
    private Date date;
    private int state;
    private int shippingFees;
    private String discountCode;

    public Order() {}

    public Order(Cart cart, String userId, int shippingFees) {
        this.content = cart.getProducts();
        this.discountCode = cart.getDiscountCode();
        this.customerId = userId;
        this.shippingFees = shippingFees;

    }


    public HashMap<Integer, Integer> getContent() {
        return content;
    }

    public void setContent(HashMap<Integer, Integer> content) {
        this.content = content;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getShippingFees() {
        return shippingFees;
    }

    public void setShippingFees(int shippingFees) {
        this.shippingFees = shippingFees;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
