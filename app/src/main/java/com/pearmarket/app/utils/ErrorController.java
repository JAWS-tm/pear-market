package com.pearmarket.app.utils;

import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ErrorController extends Controller {
    final ErrorManager errorManager;

    public ErrorController(HttpServletRequest request, HttpServletResponse response, ErrorManager eManager) {
        super(request, response);

        this.errorManager = eManager;

        this.setJspLink("/jsp/pages/error.jsp");
        this.setTitle(errorManager.getTitle());
        this.setStyleFiles(new String[] {"style", "responsive"});
    }

    @Override
    public void process() throws ServletException, IOException {
        request.setAttribute("errorTitle", errorManager.getTitle());
        request.setAttribute("errorMessage", errorManager.getDescription());

        render();
    }
}
