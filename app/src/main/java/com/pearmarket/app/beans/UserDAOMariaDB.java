package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;
import java.util.ArrayList;

public class UserDAOMariaDB implements UserDAO {
    private final DAOFactory daoFactory;

    public UserDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }


    @Override
    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            Statement stmt = connection.createStatement();
            ResultSet result = stmt.executeQuery("SELECT email, name, firstname, is_admin, is_blocked FROM users");

            while (result.next()) {
                User user = new User();
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                user.setFirstname(result.getString("firstname"));
                user.setAdmin(result.getBoolean("is_admin"));
                user.setBlocked(result.getBoolean("is_blocked"));

                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return users;
    }

    @Override
    public User getUser(String email) {
        User user = null;

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT email, name, firstname, address, is_admin, is_blocked, phone FROM users WHERE email=?"
            );

            stmt.setString(1, email);
            ResultSet res = stmt.executeQuery();

            if (res.first()) {
                user = new User();
                user.setFirstname(res.getString("firstname"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));
                user.setAddress(res.getString("address"));
                user.setPhone(res.getString("phone"));
                user.setAdmin(res.getBoolean("is_admin"));
                user.setBlocked(res.getBoolean("is_blocked"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return user;
    }

    @Override
    public User tryConnect(String email, String password) {
        User user = null;

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users WHERE email = ?");

            stmt.setString(1, email);
            ResultSet result = stmt.executeQuery();

            if (result.first() && BCrypt.checkpw(password, result.getString("password"))) {
                user = new User();
                user.setEmail(result.getString("email"));
                user.setName(result.getString("name"));
                user.setFirstname(result.getString("firstname"));
                user.setAddress(result.getString("address"));
                user.setAdmin(result.getBoolean("is_admin"));
                user.setPhone(result.getString("phone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return user;
    }

    @Override
    public User createAccount(String email, String password, String name, String firstname) {
        User newUser = null;

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO users(email, password, name, firstname) " +
                            "VALUES (?, ?, ?, ?)"
            );

            stmt.setString(1, email);
            stmt.setString(2, BCrypt.hashpw(password, BCrypt.gensalt()));
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
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return newUser;
    }

    @Override
    public void deleteAccount(String userEmail) {
        Connection connection= null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "DELETE FROM users WHERE email=?;"
            );
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    // TODO: statement builder pour regrouper les requêtes en une seule et permettre une flexibilité
    @Override
    public void changeAddress(String userEmail, String address) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET address=? WHERE email=?;"
            );
            stmt.setString(1, address);
            stmt.setString(2, userEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void changePhone(String userEmail, String phone) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET phone=? WHERE email=?;"
            );
            stmt.setString(1, phone);
            stmt.setString(2, userEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void toggleAdmin(String userEmail) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET is_admin=!is_admin WHERE email=?;"
            );
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void toggleBlocked(String userEmail) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();

            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE users SET is_blocked=!is_blocked WHERE email=?;"
            );
            stmt.setString(1, userEmail);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }
}
