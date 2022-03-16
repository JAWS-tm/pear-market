package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductController extends Controller {

    final ProductDAO productDAO;

    public EditProductController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/edit-product.jsp");

        this.setStyleFiles(new String[]{"edit-product", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        if (!getLoggedUser().getAdmin()) {
            redirect("/");
            return;
        }


        String productId = request.getParameter("id");
        if (request.getMethod().equals("POST"))
            handleForm(productId);


        if (productId != null) {
            request.setAttribute("product", productDAO.getProductById(Integer.parseInt(productId)));
            this.setTitle("Modifier le produit #"+productId);
        } else
            this.setTitle("Ajouter un produit");
    }

    /**
     * Gère formulaire et envoi les modifications sur la bdd
     * @param productId l'id du produit a modifier si c'est une update
     * @throws ServletException
     * @throws IOException
     */
    private void handleForm(String productId) throws ServletException, IOException {
        Product product = fillBean();
        if (product != null) {
            if (request.getParameter("add") != null) {
                productDAO.addProduct(product);
            } else if (request.getParameter("update") != null) {
                product.setId(Integer.parseInt(productId));
                productDAO.updateProduct(product);
            }
            redirect("/account");
        }
    }

    /**
     * Rempli l'objet et vérifie l'entrée de l'utilisateur
     * @return l'objet produit rempli avec les données
     */
    private Product fillBean() {
        Product product = null;

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String image = request.getParameter("image");

        int quantity, category;
        float price;
        try {
            price = Float.parseFloat(request.getParameter("price"));
            quantity = Integer.parseInt(request.getParameter("quantity"));
            category = Integer.parseInt(request.getParameter("category"));
        }catch (NumberFormatException e) {
            request.setAttribute("formError", "Saisissez des nombre dans les cases prix et quantité");
            return null;
        }

        // TODO : champs en number ou gestion erreur !!
        if (
                (name != null && !name.isEmpty()) &&
                (category > 0) &&
                (price > 0) &&
                (quantity >= 0) &&
                (description != null && !description.isEmpty()) &&
                (image != null && !image.isEmpty())
        ) {
            product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            product.setDescription(description);
            product.setImageSrc(image);

            Category newCategory = new Category();
            newCategory.setId(category);
            product.setCategory(newCategory);
        }
        else
            request.setAttribute("formError", "Veuillez remplir tous les champs !");


        return product;
    }
}
