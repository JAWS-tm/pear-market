package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AccountController extends Controller {

    public AccountController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/account.jsp");

        this.setTitle("Mon Compte");
        this.setStyleFiles(new String[] {"account", "responsive"});
        this.setWhiteNavBar(true);
    }

    @Override
    public void process() {




    }
}
