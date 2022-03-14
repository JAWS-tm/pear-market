package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

public class EditProductController extends Controller {

    final ProductDAO productDAO;
    final CategoryDAO categoryDAO;
    public EditProductController(HttpServletRequest request, HttpServletResponse response){
        super(request, response);

        this.setJspLink("/jsp/pages/edit-product.jsp");

        this.setTitle("edit product");
        this.setStyleFiles(new String[] {"edit-product", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        categoryDAO = daoFactory.getCategoryDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() {

        String productId = request.getParameter("id");



        request.setAttribute("categories", categoryDAO.getCategories());

        if (request.getMethod().equals("POST")){

            Product product = new Product();
            Category newCategorie = new Category();

            String productName = request.getParameter("productName");
            String productCategory = request.getParameter("productCategory");
            String productPrice = request.getParameter("productPrice");
            String productQuantity = request.getParameter("productQuantity");
            String productDescription = request.getParameter("productDescription");
            String productImage = request.getParameter("productImage");

            if (productName != null || productCategory != null || productPrice != null || productQuantity != null) {
                newCategorie.setId(Integer.parseInt(productCategory));
                product.setCategory(newCategorie);

                product.setId(Integer.parseInt(productId));
                product.setName(productName);
                product.setPrice(Float.parseFloat(productPrice));
                product.setQuantity(Integer.parseInt(productQuantity));
                product.setDescription(productDescription);
                if (productImage != null) {
                    product.setImageSrc(request.getParameter(productImage));
                    Part imageFile = request.getPart(productImage); // "file" ?

                }


                if (request.getParameter("add") != null) {
                    productDAO.addProduct(product);
                    System.out.println("on ajoute un produit");
                }
                else if (request.getParameter("update") != null) {
                    productDAO.updateProduct(product);
                    System.out.println("on edit un produit");
                }
            }
            else
                System.out.println("il manque un champ lors de la mise à jour d'un produit");
        }


        if (productId != null) {
            request.setAttribute("product", productDAO.getProductById(Integer.parseInt(productId)));
        }
        else
            request.setAttribute("product", new Product());
    }
}
