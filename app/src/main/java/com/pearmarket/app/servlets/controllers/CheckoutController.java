package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutController extends Controller {

    public CheckoutController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void process() throws ServletException, IOException {
        this.setJspLink("/jsp/pages/checkout.jsp");

        this.setTitle("Paiement");
        this.setStyleFiles(new String[] {"checkout","responsive"});
        this.setWhiteNavBar(true);

        render();
    }
}
