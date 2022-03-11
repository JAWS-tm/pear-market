package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserDAOMariaDB implements UserDAO {
    private final DAOFactory daoFactory;

    public UserDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public User getUser(int id) {
        return null;
    }

    @Override
    public User tryConnect(String email, String password) {
        return null;
    }

    @Override
    public User createAccount(String email, String password, String name, String firstname) {
        User newUser = null;

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO users(email, password, name, firstname) " +
                            "VALUES (?, ?, ?, ?)"
            )
        ) {
            stmt.setString(1, email);
            stmt.setString(2, password);
            stmt.setString(3, name);
            stmt.setString(4, firstname);
            if (stmt.executeUpdate() != 0){
                newUser = new User();
                newUser.setEmail(email);
                newUser.setName(name);
                newUser.setFirstname(firstname);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // error code 1062 si duplicate entry
        }

        return newUser;
    }
}
