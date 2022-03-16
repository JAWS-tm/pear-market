package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Product;

import java.util.ArrayList;

public interface ProductDAO {
    ArrayList<Product> getProducts();
    ArrayList<Product> getProducts(boolean distinctCategories);
    ArrayList<Product> getProductsByCategory(int categoryId);
    Product getProductById(int id);
    void addProduct(Product product);
    void updateQuantity(int newQuantity, int productId);
    void updateProduct(Product product);
    void deleteProduct(int productId);
    // Get bestsellers
}
