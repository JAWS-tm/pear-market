package com.pearmarket.app.beans;

import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.beans.elements.Product;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAOMariaDB implements ProductDAO {
    private final DAOFactory daoFactory;

    public ProductDAOMariaDB(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public ArrayList<Product> getProducts() {
        return getProducts(false);
    }

    @Override
    public ArrayList<Product> getProducts(boolean distinct) {
        ArrayList<Product> productsList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT products.*, categories.name as catName FROM products, categories WHERE products.category_id = categories.id ORDER BY products.quantity = 0 ASC ;");

            while (result.next()) {
                productsList.add(fillProduct(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return productsList;
    }

    private Product fillProduct(ResultSet result) throws SQLException {
        Product product = new Product();
        product.setId(result.getInt("id"));
        product.setName(result.getString("name"));
        product.setImageSrc(result.getString("image"));
        product.setPrice(result.getInt("price"));
        product.setQuantity(result.getInt("quantity"));
        product.setAttributes(result.getString("attributes"));

        Category cat = new Category();
        cat.setId(result.getInt("category_id"));
        cat.setName(result.getString("catName"));
        product.setCategory(cat);

        return product;
    }

    @Override
    public ArrayList<Product> getMostPopular() {
        ArrayList<Product> productsList = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(
                    "SELECT p.*, c.name as catName, SUM(co.quantity) AS totalQuantity " +
                            "FROM content_orders co " +
                                "INNER JOIN products p on co.product_id = p.id " +
                                "INNER JOIN categories c on p.category_id = c.id " +
                            "GROUP BY product_id " +
                            "ORDER BY SUM(co.quantity) DESC"
            );

            while (result.next()) {
                productsList.add(fillProduct(result));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return productsList;
    }


    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId) {
        return getProductsByCategory(categoryId, 0);
    }

    @Override
    public ArrayList<Product> getProductsByCategory(int categoryId, int limit) {
        ArrayList<Product> products = new ArrayList<>();

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "SELECT products.id, products.category_id, products.name, products.price, products.image, products.quantity, products.attributes, categories.name as catName " +
                            "FROM products, categories " +
                            "WHERE products.category_id = ? && products.category_id = categories.id " +
                            "ORDER BY products.quantity = 0 ASC, id ASC " +
                            (limit > 0 ? "LIMIT " + limit : "")
            );
            stmt.setInt(1, categoryId);
            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                Product product = new Product();
                product.setId(result.getInt("id"));
                product.setName(result.getString("name"));
                product.setCategory(new Category(result.getInt("category_id"), result.getString("catName")));
                product.setPrice(result.getInt("price"));
                product.setImageSrc(result.getString("image"));
                product.setAttributes(result.getString("attributes"));
                product.setQuantity(result.getInt("quantity"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return products;
    }

    @Override
    public Product getProductById(int id) {
        Product product = null;

        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT products.id, products.category_id, products.name, products.description, products.quantity, products.price, products.image, products.attributes, categories.name as catName " +
                            "FROM products, categories " +
                            "WHERE category_id = categories.id && products.id = ?"
            );
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();

            if (result.first()) {
                Category cat = new Category(result.getInt("category_id"), result.getString("catName"));

                product = new Product(result.getInt("id"), cat, result.getString("name"), result.getString("description"), result.getString("image"), result.getInt("price"), result.getInt("quantity"), result.getString("attributes"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }

        return product;
    }

    @Override
    public void addProduct(Product product) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "INSERT INTO products(category_id, name, image, description, price, quantity)" +
                            "VALUES(?, ?, ?, ?, ?, ?);"
            );
            stmt.setInt(1, product.getCategory().getId());
            stmt.setString(2, product.getName());
            stmt.setString(3, product.getImageSrc());
            stmt.setString(4, product.getDescription());
            stmt.setFloat(5, product.getPrice());
            stmt.setInt(6, product.getQuantity());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void updateQuantity(int newQuantity, int productId) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                "UPDATE products SET quantity=? WHERE id=?;"
            );
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void decrementQuantity(int productId, int quantity) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE products SET quantity=quantity-? WHERE id=? AND quantity>0;"
            );
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }

    @Override
    public void updateProduct(Product product) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                    "UPDATE products SET name =?, category_id= ?, price=?, quantity=?, description=?, image=? WHERE id=?;"
            );
            stmt.setString(1, product.getName());
            stmt.setInt(2, product.getCategory().getId());
            stmt.setFloat(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.setString(5, product.getDescription());
            stmt.setString(6, product.getImageSrc());
            stmt.setInt(7, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }


    @Override
    public void deleteProduct(int productId) {
        Connection connection = null;
        try {
            connection = daoFactory.getConnection();
            PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM products WHERE id = ?;"
            );
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
        }
    }
}