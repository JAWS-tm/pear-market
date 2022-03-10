package com.pearmarket.app.servlets;


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

        String id = request.getParameter("link");

        if (id == null || id == "")
            id = "home";
        request.setAttribute("pageId", id);



        Controller controller = null;
        try {
            Constructor c = Class.forName(getController(id)).getConstructor(HttpServletRequest.class, HttpServletResponse.class);
            controller = (Controller) c.newInstance(request, response);
        } catch (Exception e) {
            e.printStackTrace();

            //request.setCharacterEncoding("UTF-8");
            /*request.setAttribute("pageContent", "/jsp/pages/404.jsp");
           // 404 ou erreur logicielle request.setAttribute("errorMessage", "Une erreur s'est produite lors du traitement de votre demande. \nSi l'erreur persiste veuillez contacter un administrateur.");
            request.getRequestDispatcher("/jsp/layout.jsp").forward(request, response);*/

            /// Creer un controlleur erreur

            request.setAttribute("hasError", true);
            request.setAttribute("title", "Pear Market - Erreur 404");
            request.setAttribute("styleFiles", new String[] {"style","responsive"});
            request.setAttribute("errorMessage", "Une erreur s'est produite lors du traitement de votre demande. \nSi l'erreur persiste veuillez contacter un administrateur.");
            request.getRequestDispatcher("/jsp/layout.jsp").forward(request, response);
        }

        System.out.println(id);
        System.out.println(controller.toString());

        try {
            controller.process();

            controller.render();
        } catch (ErrorManager e) {
            // Afficher Page d'erreur
            System.out.println(e.getType() + " - " + e.getMessage());
        }


    }

    static String getController(String s){
        String[] parts = s.split("-");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return "com.pearmarket.app.servlets.controllers." + camelCaseString + "Controller";
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
