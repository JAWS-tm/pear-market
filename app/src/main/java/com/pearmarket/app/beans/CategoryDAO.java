package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;

import java.util.ArrayList;

/***
 * Gère la bdd "categories"
 */
public interface CategoryDAO {
    /**
     * Récupère toutes les catégories
     * @return la liste des catégories
     */
    ArrayList<Category> getCategories();

    /**
     * Récupère les infos d'une catégorie
     * @param id de la catégorie
     * @return infos de la catégorie
     */
    Category getCategory(int id);
}
