package com.pearmarket.app.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Créer et gère la base de donnée
 */
public class DAOFactory {
    private static volatile DAOFactory instance = null;
    private String url;
    private String user;
    private String password;

    private DAOFactory() {}

    /**
     * Récupère l'instance actuelle de DAOFactory ou en créer une
     * @return l'instance
     */
    public static synchronized DAOFactory getInstance() {
        if (DAOFactory.instance == null) {
            synchronized(DAOFactory.class) {
                if (DAOFactory.instance == null) {
                    instance = new DAOFactory();
                }
            }
        }
        return DAOFactory.instance;
    }

    /**
     * Récupère une connexion
     * @return la connexion à la bdd récupérée
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    /**
     * Défini les paramètres et charge le driver pour MariaDB
     * @param url de la bdd
     * @param user de connexion à la bdd
     * @param password de connexion à la bdd
     */
    public void setParamsMariaDB(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Type de bdd pris en charge par notre site
     */
    public enum DBType {
        MariaDB
    }

    /**
     * Renvoie le gestionaire de bdd "products"
     * @param type type à charger
     * @return le gestionnaire
     */
    public ProductDAO getProductDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new ProductDAOMariaDB(this);
            default:
                return null;
        }
    }

    /**
     * Renvoie le gestionaire de bdd "categories"
     * @param type type à charger
     * @return le gestionnaire
     */
    public CategoryDAO getCategoryDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new CategoryDAOMariaDB(this);
            default:
                return null;
        }
    }

    /**
     * Renvoie le gestionaire de bdd "users"
     * @param type type à charger
     * @return le gestionnaire
     */
    public UserDAO getUserDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new UserDAOMariaDB(this);
            default:
                return null;
        }
    }

    /**
     * Renvoie le gestionaire de bdd "orders"
     * @param type type à charger
     * @return le gestionnaire
     */
    public OrderDAO getOrderDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new OrderDAOMariaDB(this);
            default:
                return null;
        }
    }
}
