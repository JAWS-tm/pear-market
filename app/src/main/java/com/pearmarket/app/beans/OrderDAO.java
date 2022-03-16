package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Order;

import java.util.ArrayList;

/**
 * Gère la base de donnée "orders et content_orders"
 */
public interface OrderDAO {
    /**
     * Récupère les données d'une commande
     * @param orderId id de la commande
     * @return Order contenant toute la commande
     */
    Order getOrder(int orderId);

    /**
     * Récupère toutes les commande d'un utilisateur
     * @param userEmail l'email de l'utilisateur
     * @return La liste de commandes
     */
    ArrayList<Order> getUserOrders(String userEmail);

    /**
     * Retourne toutes les commandes
     * @return liste de toutes les commandes
     */
    ArrayList<Order> getOrders();

    /**
     * Créer une commande et ajoute les données dans {@code content_orders}
     * @param order Les données de la commande
     * @return l'id de la commande créée
     */
    int createOrder(Order order);

    /**
     * Met à jour l'état de la commande
     * @param id id de la commande
     * @param state nouvel état de commande
     * @return si la mise à jour à fonctionnée
     */
    boolean updateState(int id, int state);
}
