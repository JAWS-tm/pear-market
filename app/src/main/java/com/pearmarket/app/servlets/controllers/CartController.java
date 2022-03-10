package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartController extends Controller {
    public CartController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/cart.jsp");

        this.setTitle("Panier");
        this.setStyleFiles(new String[] {"cart", "responsive"});
        this.setWhiteNavBar(true);
    }

    @Override
    public void process() {


    }
}
