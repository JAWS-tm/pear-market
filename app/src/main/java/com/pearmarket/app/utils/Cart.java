package com.pearmarket.app.utils;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Product;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Gestionnaire de panier
 *
 *  - Le panier est stocké en session
 */
public class Cart implements Serializable {

    /**
     * products est utilisé pour le stockage et la gestion du panier
     * computedProducts contient lui toutes les données, il est utilisé pour l'affichage
     *
     * La key des HashMap est le produit (int ou Product) et la value est la quantité souhaitée
     */
    private final LinkedHashMap<Integer, Integer> products;
    private LinkedHashMap<Product, Integer> computedProducts;
    private boolean cartHasChange = true;
    private String discountCode;
    private final ProductDAO productDAO;

    public Cart() {
        products = new LinkedHashMap<>();
        productDAO = DAOFactory.getInstance().getProductDAO(DAOFactory.DBType.MariaDB);
    }

    /**
     * Ajoute un produit au panier dans une quantité donnée
     * @param productId l'id du produit a ajouter
     * @param quantity la quantité
     */
    public void addProduct(int productId, int quantity) {
        products.put(productId, products.getOrDefault(productId, 0) + quantity);
        cartHasChange = true;
    }

    /**
     * Change la quantité souhaité d'un produit
     * Le supprime si la quantité est nulle
     * @param productId l'id du produit a modifier
     * @param quantity la nouvelle quantité
     * @return si le changement a fonctionné
     */
    public boolean changeQuantity(int productId, int quantity) {
        if (quantity <= 0)
             return removeProduct(productId);

        if (products.containsKey(productId)) {
            products.computeIfPresent(productId, (k, v) -> quantity);
            cartHasChange = true;
            return true;
        }
        else
            return false;

    }

    /**
     * Supprime un produit du panier
     * @param productId l'id du produit a supprimer
     * @return si la suppression a fonctionnée
     */
    public boolean removeProduct(int productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
            cartHasChange = true;
            return true;
        }
        return false;
    }

    /**
     * Récupère le panier complet (avec les données des produits)
     * @return la liste complète
     */
    public LinkedHashMap<Product, Integer> getComputedProducts() {
        return getComputedProducts(false);
    }

    /**
     * Récupère le panier complet (avec les données des produits)
     * @param forced force le rafraichissement de la liste
     * @return la liste complète
     */
    public LinkedHashMap<Product, Integer> getComputedProducts(boolean forced) {
        // prevent useless dao query
        if (computedProducts == null || cartHasChange || forced) {
            LinkedHashMap<Product, Integer> productsList = new LinkedHashMap<>();

            for(Map.Entry<Integer, Integer> entry : products.entrySet()) {
                int prodId = entry.getKey();
                Integer quantity = entry.getValue();

                productsList.put(productDAO.getProductById(prodId), quantity);
            }
            computedProducts = productsList;
        }

        return computedProducts;
    }

    /**
     * Vérifie que tous les produits sont en stock dans le panier
     * @return si ils sont tous en stock
     */
    public boolean allProductsInStock() {
        LinkedHashMap<Product, Integer> productsList = getComputedProducts(true);

        for(Map.Entry<Product, Integer> entry : productsList.entrySet()) {
            if (entry.getKey().getQuantity() <= 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne le panier non traité (seulement l'id du produit et sa quantité)
     * @return le panier non traité
     */
    public LinkedHashMap<Integer, Integer> getProducts() {
        return products;
    }

    /**
     * Calcul le total du panier
     * @return le total
     */
    public int getTotalPrice() {
        int total = 0;
        for (Map.Entry<Product, Integer> entry : getComputedProducts().entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
        }
        return total;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
