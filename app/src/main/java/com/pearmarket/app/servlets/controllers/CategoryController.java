package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class CategoryController extends Controller {
    ProductDAO productDAO;
    public CategoryController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        this.setJspLink("/jsp/pages/category.jsp");

        this.setTitle("Catégorie");
        this.setStyleFiles(new String[] {"category","responsive"});
        this.setWhiteNavBar(true);


        int categoryId = Integer.parseInt(request.getParameter("id")); // check if id exist

        ArrayList<Product> products;
        if (categoryId != 0 && !(products = productDAO.getProductsByCategory(categoryId)).isEmpty())
        {
            request.setAttribute("products", products);
        }
        else
        {
            // erreur ou pas de products
        }

        render();
    }
}
