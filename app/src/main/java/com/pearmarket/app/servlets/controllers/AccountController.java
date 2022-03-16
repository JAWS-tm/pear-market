package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.OrderDAO;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountController extends Controller {
    private final ProductDAO productDAO;
    private final UserDAO userDAO;
    private final OrderDAO orderDAO;

    public AccountController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/account.jsp");

        this.setTitle("Mon Compte");
        this.setStyleFiles(new String[] {"account", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
        orderDAO = daoFactory.getOrderDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        User user = getLoggedUser();
        if (getLoggedUser() == null) {
            redirect("/sign-in");
            return;
        }

        request.setAttribute("selectedTab", "account-infos");
        if (request.getMethod().equals("POST"))
            processPost(user);
        else
            processGet(user);


        if(user.getAdmin()) {
            request.setAttribute("products", productDAO.getProducts());
            request.setAttribute("users", userDAO.getUsers());
            request.setAttribute("allOrders", orderDAO.getOrders());
        }

        request.setAttribute("orders", orderDAO.getUserOrders(user.getEmail()));
    }

    /**
     * Gère les requêtes get
     * @param user utilisateur connecté
     * @throws IOException
     * @throws ServletException
     */
    private void processGet(User user) throws IOException, ServletException {
        if (!user.getAdmin()){
            response.setStatus(400);
            return;
        }

        String action = request.getParameter("id");

        if (action == null)
            return;

        switch (action) {
            case "delete":
                int deleteProductId = Integer.parseInt(request.getParameter("data"));
                productDAO.deleteProduct(deleteProductId);

                request.setAttribute("selectedTab", "admin-products");
                redirect("/account");
                break;

        }
    }

    /**
     * Gère les requêtes post
     * @param user utilisateur connecté
     */
    private void processPost(User user) {

        if (!user.getAdmin()){
            response.setStatus(400);
            return;
        }


        String action = request.getParameter("action");

        if (action == null)
            return;

        switch (action) {
            case "changeQuantity":
                int newQuantity = Integer.parseInt(request.getParameter("newQuantity"));
                int productId = Integer.parseInt(request.getParameter("productId"));

                productDAO.updateQuantity(newQuantity, productId);

                request.setAttribute("selectedTab", "admin-products");

                break;

            case "deleteUser":
                String userEmail = request.getParameter("userId");
                userDAO.deleteAccount(userEmail);

                request.setAttribute("selectedTab", "users-list");
                break;


            case "toggleAdmin":
                String userId = request.getParameter("userId");
                userDAO.toggleAdmin(userId);


                User userUpdated = userDAO.getUser(userId);
                if (userUpdated == null)
                    response.setStatus(400);
                else
                    response.setStatus(200);
                break;

            case "toggleBlocked":
                userId = request.getParameter("userId");
                userDAO.toggleBlocked(userId);

                userUpdated = userDAO.getUser(userId);
                if (userUpdated == null)
                    response.setStatus(400);
                else
                    response.setStatus(200);
                break;

            case "changeOrderState":
                try {
                    int orderId = Integer.parseInt(request.getParameter("userId"));
                    int state = Integer.parseInt(request.getParameter("state"));

                    if (orderDAO.updateState(orderId, state))
                        response.setStatus(200);
                    else
                        response.setStatus(400);

                } catch (NumberFormatException e) {
                    response.setStatus(400);

                }

                break;
        }
    }
}
