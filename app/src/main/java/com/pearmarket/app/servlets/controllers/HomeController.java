package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends Controller {

    private ProductDAO productDAO;
    private CategoryDAO categoryDAO;

    public HomeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        categoryDAO = daoFactory.getCategoryDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        this.setJspLink("/jsp/pages/index.jsp");
        this.setStyleFiles(new String[] {"home","responsive"});


        request.setAttribute("productsList", productDAO.getProducts());
        request.setAttribute("categories", categoryDAO.getCategories());

        render();
    }
}
