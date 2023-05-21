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
    private final String host;
    private final String port;
    private final String user;
    private final String password;
    private final String dbName;

    private DAOFactory() {
        host = System.getenv("DB_HOST");
        port = System.getenv("DB_PORT");
        user = System.getenv("DB_USER");
        password = System.getenv("DB_PASSWORD");
        dbName = System.getenv("DB_NAME");
    }

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
     */
    public void loadDriverMariaDB() {
        url = "jdbc:mariadb://" + host + ":" + port + "/" + dbName;
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
                loadDriverMariaDB();
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
                loadDriverMariaDB();
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
                loadDriverMariaDB();
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
                loadDriverMariaDB();
                return new OrderDAOMariaDB(this);
            default:
                return null;
        }
    }
}
