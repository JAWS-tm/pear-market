package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController extends Controller {

    private final ProductDAO productDAO;

    public HomeController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/index.jsp");
        this.setStyleFiles(new String[] {"home","responsive"});

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("productsList", productDAO.getProducts());

        String id = request.getParameter("id");
        if (id != null && id.equals("disconnect")) {
            request.getSession().setAttribute("loggedUser", null);
            redirect("/");
            return;
        }
    }
}
