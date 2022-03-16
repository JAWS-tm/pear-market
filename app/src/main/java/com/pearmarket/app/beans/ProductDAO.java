package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Product;

import java.util.ArrayList;

public interface ProductDAO {
    ArrayList<Product> getProducts();
    ArrayList<Product> getMostPopular();
    ArrayList<Product> getProductsByCategory(int categoryId);
    ArrayList<Product> getProductsByCategory(int categoryId, int limit);
    Product getProductById(int id);
    void addProduct(Product product);
    void updateQuantity(int newQuantity, int productId);
    void decrementQuantity(int productId, int quantity);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    // Get bestsellers
}
