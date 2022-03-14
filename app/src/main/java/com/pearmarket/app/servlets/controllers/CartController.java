package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.utils.Cart;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.utils.ErrorManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartController extends Controller {
    ProductDAO productDAO;

    public CartController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/cart.jsp");

        this.setTitle("Panier");
        this.setStyleFiles(new String[] {"cart", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ErrorManager {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        request.setAttribute("cartProducts", cart.getComputedProducts());

        System.out.println("product id" +request.getParameter("productId"));
        System.out.println("id page" + request.getParameter("id"));

        if (request.getMethod().equals("POST")) {
            String action = request.getParameter("id");

            switch (action) {
                case "delete":
                    int productId = parseInt(request.getParameter("productId"));

                    if (!cart.removeProduct(productId))
                        response.setStatus(400);

                    break;

                case "changeQuantity":
                    productId = parseInt(request.getParameter("productId"));
                    int quantity = parseInt(request.getParameter("quantity"));

                    if (!cart.changeQuantity(productId, quantity))
                        response.setStatus(400);


                    break;
            }
        }

    }
}
