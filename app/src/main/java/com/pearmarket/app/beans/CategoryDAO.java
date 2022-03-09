package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;

import java.util.ArrayList;

public interface CategoryDAO {
    ArrayList<Category> getCategories();
    Category getCategory(int id);
}
