package com.pearmarket.app.beans.elements;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
    private class CartRow {
        protected int productId;
        protected int quantity;

        public CartRow(int productId, int quantity) {
            this.productId = productId;
            this.quantity = quantity;
        }
    }

    //private ArrayList<CartRow> products;
    private HashMap<Integer, Integer> products;
    private ProductDAO productDAO;

    public Cart() {
        //products = new ArrayList<>();
        products = new HashMap<>();
        productDAO = DAOFactory.getInstance().getProductDAO(DAOFactory.DBType.MariaDB);
    }

    public void addProduct(int productId, int quantity) {
        products.put(productId, products.getOrDefault(productId, 0) + quantity);
    }

    public void changeQuantity(int productId, int quantity) {
        products.computeIfPresent(productId, (k, v) -> quantity);
    }

    public void removeProduct(int productId) {
        products.remove(productId);
    }

    public HashMap<Product, Integer> getComputedProducts() {
        HashMap<Product, Integer> productsList = new HashMap<>();

        for(Map.Entry<Integer, Integer> entry : products.entrySet()) {
            int prodId = entry.getKey();
            Integer quantity = entry.getValue();

            productsList.put(productDAO.getProductById(prodId), quantity);
        }

        return productsList;
    }
}
