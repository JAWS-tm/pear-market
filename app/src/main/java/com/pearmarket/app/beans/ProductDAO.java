package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Product;

import java.util.ArrayList;

public interface ProductDAO {
    ArrayList<Product> getProducts();
    ArrayList<Product> getProductsByCategory(int categoryId);
    Product getProductById(int id);
    // Get bestsellers
}
