package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountController extends Controller {

    final ProductDAO productDAO;
    public AccountController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/account.jsp");

        this.setTitle("Mon Compte");
        this.setStyleFiles(new String[] {"account", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, ErrorManager, IOException {
        if (getLoggedUser() == null) {
            redirect("/sign-in");
        }
        else {
            User user = (User) request.getSession().getAttribute("loggedUser");
            request.setAttribute("user", user);
        }


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
            }
        }

        request.setAttribute("products", productDAO.getProducts());

        //request.setAttribute("users", );


    }
}
