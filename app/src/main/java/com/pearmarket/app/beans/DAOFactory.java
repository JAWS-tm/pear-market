package com.pearmarket.app.beans;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOFactory {
    private static volatile DAOFactory instance = null;
    private String url;
    private String user;
    private String password;

    private DAOFactory() {}

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

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

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

    public enum DBType {
        MariaDB
    }

    public ProductDAO getProductDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new ProductDAOMariaDB(this);
            default:
                return null;
        }
    }

    public CategoryDAO getCategoryDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new CategoryDAOMariaDB(this);
            default:
                return null;
        }
    }

    public UserDAO getUserDAO(DBType type) {
        switch (type) {
            case MariaDB:
                setParamsMariaDB("jdbc:mariadb://localhost:3306/projet_shop", "root", "");
                return new UserDAOMariaDB(this);
            default:
                return null;
        }
    }
}
