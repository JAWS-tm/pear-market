package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountController extends Controller {

    final ProductDAO productDAO;
    final UserDAO userDAO;
    public AccountController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/account.jsp");

        this.setTitle("Mon Compte");
        this.setStyleFiles(new String[] {"account", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        User user = getLoggedUser();
        if (getLoggedUser() == null) {
            redirect("/sign-in");
            return;
        }
        request.setAttribute("user", user);


        String id = request.getParameter("id");

        if (id != null) {
            switch (id) {
                case "changeQuantity":
                    if (request.getMethod().equals("POST")) {
                        int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
                        int productId = Integer.parseInt(request.getParameter("productId"));

                        productDAO.updateQuantity(newQuantity, productId);
                    }
                    break;

                case "delete":
                    int deleteProductId = Integer.parseInt(request.getParameter("data"));
                    productDAO.deleteProduct(deleteProductId);
                    break;

                case "deleteUser":
                    String userEmail = request.getParameter("deleteUser");
                    userDAO.deleteAccount(userEmail);
                    break;
            }
        }

        request.setAttribute("products", productDAO.getProducts());

        request.setAttribute("users", userDAO.getUsers());
    }
}
