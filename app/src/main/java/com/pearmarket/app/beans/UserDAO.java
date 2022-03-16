package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.User;

import java.util.ArrayList;

/**
 * Gère les données de la table "users"
 */
public interface UserDAO {
    /**
     * Récupère la liste de tous les utilisateurs
     * @return tous les utilisateurs
     * @implNote rajouter un LIMIT pour faire des affichages de pages (1, 2, 3)
     */
    ArrayList<User> getUsers();

    /**
     * Récupère les données des utilisateurs
     * @param email l'email (id) de l'utilisateur à récupérer
     * @return données de l'user ou null si il n'y a pas d'utilisateur possédant cet email
     */
    User getUser(String email);

    /**
     * Vérifie si l'utilisateur a un compte et si le mot de passe est correct et le récupère
     * @param email de l'utilisateur
     * @param password de l'utilisateur
     * @return données de l'user ou null si les informations ou l'utilisateur n'existe pas
     */
    User tryConnect(String email, String password);

    /**
     * Créer un compte avec les données en paramètre
     * @param email du compte
     * @param password du compte
     * @param name du compte
     * @param firstname du compte
     * @return l'utilisateur crée ou null si un compte existe déjà
     */
    User createAccount(String email, String password, String name, String firstname);

    /**
     * Supprime un compte utilisateur
     * @param userEmail l'email du compte
     */
    void deleteAccount(String userEmail);

    /**
     * Change l'adresse associée a un compte
     * @param userEmail l'email du compte
     * @param address la nouvelle adresse
     */
    void changeAddress(String userEmail, String address);

    /**
     * Change le numéro de télephone associé a un compte
     * @param userEmail l'email du compte
     * @param phone le nouveau télephone
     */
    void changePhone(String userEmail, String phone);

    /**
     * Inverse l'état administrateur du compte
     * @param userEmail l'email du compte
     */
    void toggleAdmin(String userEmail);

    /**
     * Inverse l'état bloqué du compte
     * @param userEmail l'email du compte
     */
    void toggleBlocked(String userEmail);
}
