package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAOMariaDB implements CategoryDAO{
    private final DAOFactory daoFactory;

    public CategoryDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();
        Connection connection = null;

        try {
            connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories;");

            while (result.next())
            {
                categories.add(new Category(result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("image")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return categories;
    }

    @Override
    public Category getCategory(int id) {
        Category category = null;

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT * FROM categories WHERE id = ?"
            );
            stmt.setInt(1, id);
            ResultSet result = stmt.executeQuery();

            if (result.first()){
                category = new Category(
                    result.getInt("id"),
                    result.getString("name"),
                    result.getString("description"),
                    result.getString("image")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return category;
    }
}
