package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoryDAOMariaDB implements CategoryDAO{
    private DAOFactory daoFactory;

    public CategoryDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Category> getCategories() {
        ArrayList<Category> categories = new ArrayList<>();

        try (
            Connection connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM categories;");
        ) {
            while (result.next())
            {
                categories.add(new Category(result.getInt("id"), result.getString("name"), result.getString("description"), result.getString("image")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getCategory(int id) {
        return null;
    }
}
