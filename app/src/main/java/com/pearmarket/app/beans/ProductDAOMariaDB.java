package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.beans.elements.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOMariaDB implements ProductDAO {
    private DAOFactory daoFactory;

    public ProductDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Product> getProducts() {
        ArrayList<Product> productsList = new ArrayList<>();

        try (
            Connection connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT products.id, products.category_id, products.name, products.price, products.image, products.attributes, categories.name as catName FROM products, categories WHERE products.category_id = categories.id;");
        ) {
            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setImageSrc(result.getString("image"));
                product.setPrice(result.getInt("price"));
                product.setAttributes(result.getString("attributes"));

                Category cat = new Category();
                cat.setId(result.getInt("category_id"));
                cat.setName(result.getString("catName"));
                product.setCategory(cat);

                productsList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productsList;
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) {
        ArrayList<Product> products = new ArrayList<>();

        try (Connection connection = daoFactory.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT products.id, products.category_id, products.name, products.price, products.image, products.attributes, categories.name as catName " +
                            "FROM products, categories " +
                            "WHERE products.category_id = ? && products.category_id = categories.id"
            );
            stmt.setInt(1, categoryId);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setCategory(new Category(result.getInt("category_id"), result.getString("catName")));
                product.setPrice(result.getInt("price"));
                product.setImageSrc(result.getString("image"));
                product.setAttributes(result.getString("attributes"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return products;
    }

    @Override
    public Product getProductById(final int id) {
        Product product = null;

        try (Connection connection = daoFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT products.id, products.category_id, products.name, products.description, products.quantity, products.price, products.image, products.attributes, categories.name as catName " +
                            "FROM products, categories " +
                            "WHERE category_id = categories.id && products.id = ?"
            );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.first())
            {
                Category cat = new Category(result.getInt("category_id"), result.getString("catName"));

                product = new Product(result.getInt("id"), cat, result.getString("name"), result.getString("description"), result.getString("image"), result.getInt("price"), result.getInt("quantity"), result.getString("attributes"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }
}
