package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductController extends Controller {
    private ProductDAO productDAO;

    public ProductController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        this.setJspLink("/jsp/pages/product.jsp");

        this.setTitle("Produit");
        this.setStyleFiles(new String[] {"product","responsive"});
        this.setWhiteNavBar(true);

        int id = Integer.parseInt(request.getParameter("id"));

        Product product;
        if (id != 0 && (product = productDAO.getProductById(id)) != null) {
            request.setAttribute("product", product);
        } else {
            // redirect to 404 or show error message (no product assigned)
        }


        render();
    }
}
