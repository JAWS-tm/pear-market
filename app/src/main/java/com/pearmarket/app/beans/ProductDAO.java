package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Product;

import java.util.ArrayList;

/**
 * Gère les données de la table "products"
 */
public interface ProductDAO {
    /**
     * Récupère tous les produits (hors ceux non actifs)
     * @return ArrayList de Product
     */
    ArrayList<Product> getProducts();

    /**
     * Récupère les produits les plus vendus en regardant dans la table "content_orders"
     * @return les produits les plus vendus
     */
    ArrayList<Product> getMostPopular();

    /**
     * Récupère tous les produits d'une catégorie
     * @param categoryId id de la catégorie
     * @return liste de produits
     */
    ArrayList<Product> getProductsByCategory(int categoryId);
    /**
     * Récupère les {@code limit} premiers produits d'une catégorie
     * @param categoryId id de la catégorie
     * @param limit nombre de résultats à récupérer
     * @return liste de produits
     */
    ArrayList<Product> getProductsByCategory(int categoryId, int limit);

    /**
     * Récupère les infos d'un produit
     * @param id id du produit
     * @return le produit récupéré ou {@code null} s'il n'a pas été trouvé
     */
    Product getProductById(int id);

    /**
     * Ajoute un produit à la bdd
     * @param product le produit à ajouter
     */
    void addProduct(Product product);

    /**
     * Change la quantité du produit
     * @param newQuantity la nouvelle quantité
     * @param productId l'id du produit
     */
    void updateQuantity(int newQuantity, int productId);

    /**
     * Décrémente la quantité du produit
     * @param productId l'id du produit
     * @param quantity la quantité a retirer
     */
    void decrementQuantity(int productId, int quantity);

    /**
     * Met à jour un produit
     * @param product les données du produit (l'id à modifier est contenu dans cet objet)
     */
    void updateProduct(Product product);

    /**
     * "Supprime" un fichier.
     * Il n'est pas supprimé, il est défini comme inactif dans la bdd (column {@code active}) pour permettre aux factures de toujours accéder à ces produits
     * @param productId
     */
    void deleteProduct(int productId);
    // Get bestsellers
}
