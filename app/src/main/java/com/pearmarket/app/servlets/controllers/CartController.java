package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.elements.Cart;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
public class CartController extends Controller {
    public CartController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/cart.jsp");

        this.setTitle("Panier");
        this.setStyleFiles(new String[] {"cart", "responsive"});
        this.setWhiteNavBar(true);
    }

    @Override
    public void process() throws IOException, ErrorManager {
        Cart cart = (Cart) request.getSession().getAttribute("cart");
        request.setAttribute("cartProducts", cart.getComputedProducts());

        System.out.println(request.getParameter("productId"));
        System.out.println(request.getParameter("id"));

        if (request.getMethod().equals("POST")) {
            String action = request.getParameter("id");

            switch (action) {
                case "delete":
                    int productId = parseInt(request.getParameter("productId"));

                    cart.removeProduct(productId);
                    break;

                case "changeQuantity":
                    productId = parseInt(request.getParameter("productId"));
                    int quantity = parseInt(request.getParameter("quantity"));

                    cart.changeQuantity(productId, quantity);
                    break;
            }
        }

    }
}
