package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProductController extends Controller {

    public EditProductController(HttpServletRequest request, HttpServletResponse response){
        super(request, response);

        this.setJspLink("/jsp/pages/edit-product.jsp");

        this.setTitle("edit product");
        this.setStyleFiles(new String[] {"edit-product", "responsive"});
        this.setWhiteNavBar(true);

    }

    @Override
    public void process() throws ServletException, IOException, ErrorManager {

    }
}
