package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpController extends Controller {

    public SignUpController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);
    }

    @Override
    public void process() throws ServletException, IOException {
        this.setJspLink("/jsp/pages/sign-up.jsp");

        this.setTitle("Inscription");
        this.setStyleFiles(new String[] {"connection","responsive"});
        this.setWhiteNavBar(true);

        render();
    }
}
