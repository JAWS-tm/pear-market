package com.pearmarket.app.beans.elements;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Cart implements Serializable {
//    private class CartRow {
//        protected int productId;
//        protected int quantity;
//
//        public CartRow(int productId, int quantity) {
//            this.productId = productId;
//            this.quantity = quantity;
//        }
//    }

    private final HashMap<Integer, Integer> products;
    private HashMap<Product, Integer> computedProducts;
    private boolean cartHasChange = true;
    private final ProductDAO productDAO;

    public Cart() {
        products = new HashMap<>();
        productDAO = DAOFactory.getInstance().getProductDAO(DAOFactory.DBType.MariaDB);
    }

    public void addProduct(int productId, int quantity) {
        products.put(productId, products.getOrDefault(productId, 0) + quantity);
        cartHasChange = true;
    }

    public boolean changeQuantity(int productId, int quantity) {
        if (quantity == 0)
             return removeProduct(productId);


        if (products.containsKey(productId)) {
            products.computeIfPresent(productId, (k, v) -> quantity);
            cartHasChange = true;
            return true;
        }
        else
            return false;

    }

    public boolean removeProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            cartHasChange = true;
            return true;
        }
        return false;
    }

    public HashMap<Product, Integer> getComputedProducts() {
        if (computedProducts == null || cartHasChange) {
            HashMap<Product, Integer> productsList = new HashMap<>();

            for(Map.Entry<Integer, Integer> entry : products.entrySet()) {
                int prodId = entry.getKey();
                Integer quantity = entry.getValue();

                productsList.put(productDAO.getProductById(prodId), quantity);
            }
            computedProducts = productsList;
        }

        return computedProducts;
    }

    public HashMap<Integer, Integer> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : getComputedProducts().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }
}
