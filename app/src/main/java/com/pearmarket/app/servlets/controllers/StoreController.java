package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class StoreController extends Controller {
    public StoreController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/store.jsp");

        this.setTitle("Nos Produits");
        this.setStyleFiles(new String[] {"style", "products-list", "responsive"});
    }

    @Override
    public void process() {

    }
}
