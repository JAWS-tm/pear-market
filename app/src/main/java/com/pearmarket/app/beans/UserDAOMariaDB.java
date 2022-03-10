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
    public Boolean createAccount(User user) {
        boolean userCreated = false;

        try (
            Connection connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO users(email, password, name, firstname) " +
                            "VALUES (?, ?, ?, ?)"
            )
        ) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setString(4, user.getFirstname());
            if (stmt.executeUpdate() != 0)
                userCreated = true;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return userCreated;
    }
}
