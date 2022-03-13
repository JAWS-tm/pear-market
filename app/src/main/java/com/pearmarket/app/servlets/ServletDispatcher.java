package com.pearmarket.app.servlets;

import com.pearmarket.app.beans.elements.Cart;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Constructor;

@WebServlet("/app")
public class ServletDispatcher extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp);
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");


        if (request.getSession().getAttribute("cart") == null)
            request.getSession().setAttribute("cart", new Cart());


        String id = request.getParameter("link");
        if (id == null || id.equals(""))
            id = "home";
        request.setAttribute("pageId", id);

        try {
            Controller controller;
            try {
                Constructor<?> c = Class.forName(getController(id)).getConstructor(HttpServletRequest.class, HttpServletResponse.class);
                controller = (Controller) c.newInstance(request, response);
            } catch (Exception e) {
                throw new ErrorManager(ErrorManager.ErrorTypes.ERROR_404);
            }

            System.out.println(id);
            System.out.println(controller);

            controller.process();
            controller.render();
        } catch (ErrorManager e) {
            ErrorController errorPage = new ErrorController(request, response, e);

            errorPage.process();
        }
    }

    static String getController(String s){
        String[] parts = s.split("-");
        StringBuilder camelCaseString = new StringBuilder();
        for (String part : parts){
            camelCaseString.append(toProperCase(part));
        }
        return "com.pearmarket.app.servlets.controllers." + camelCaseString + "Controller";
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
