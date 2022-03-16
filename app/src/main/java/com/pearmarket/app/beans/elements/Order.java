package com.pearmarket.app.beans.elements;

import com.pearmarket.app.utils.Cart;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * JavaBean d'une commande
 */
public class Order {
    /**
     * content: id de produit en key et les quantité en value
     */
    private LinkedHashMap<Product, Integer> content;
    private int id;
    private User customer;
    private Timestamp date;
    private int state;
    private int shippingFees;
    private String discountCode;

    public Order() {}

    public Order(Cart cart, String userId, int shippingFees) {
        this.content = cart.getComputedProducts();
        this.discountCode = cart.getDiscountCode();
        this.customer = new User();
        this.customer.setEmail(userId);
        this.shippingFees = shippingFees;
        this.date = new Timestamp(new Date().getTime());
    }

    /**
     * Calcul le total de tous les produits
     * @return le total
     */
    public float getTotal() {
        float total = 0;
        for(Map.Entry<Product, Integer> entry : content.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    /**
     * Créer une chaine qui contient le nom de chaque produits dans la commande (ex: Iphone, Samsung)
     * @return la chaine générée
     */
    public String getContentResume() {
        String resume = "";
        boolean firstProduct = true;
        for(Map.Entry<Product, Integer> entry : content.entrySet()) {
            resume += (!firstProduct ? ", ": "") +entry.getKey().getName() ;
            firstProduct = false;
        }
        return resume;
    }

    /**
     * @return les produits contenus dans la commande
     */
    public LinkedHashMap<Product, Integer> getContent() {
        return content;
    }

    public void setContent(LinkedHashMap<Product, Integer> content) {
        this.content = content;
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    /**
     * Génère une date en string
     * @return la chaine
     */
    public String getFormattedDate() {
        return new SimpleDateFormat("dd/MM/yyyy - HH:mm").format(date);
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
