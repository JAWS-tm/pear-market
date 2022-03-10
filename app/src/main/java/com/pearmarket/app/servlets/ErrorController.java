package com.pearmarket.app.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorController extends Controller {
    ErrorManager errorManager;

    public ErrorController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink();
        this.setTitle(errorManager.getTitle());
        this
    }

    @Override
    public void process() throws ServletException, IOException, ErrorManager {

    }
}
