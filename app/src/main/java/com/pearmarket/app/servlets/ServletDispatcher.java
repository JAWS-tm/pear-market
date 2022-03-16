package com.pearmarket.app.servlets;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.utils.Cart;
import com.pearmarket.app.utils.ErrorController;
import com.pearmarket.app.utils.ErrorManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Constructor;

@WebServlet("/app")
/**
 * Classe principale qui recoit toutes les requêtes.
 * Le site possède un URL rewriting ce qui nous permet d'obtenir des liens "user-friendly"
 * Il y a trois découpage pour l'url, voici le résultat de chacun par rapport à l'entrée:
 *      - /xxxx => /app?link=xxx
 *      - /xxxx/aaa => /app?link=xxx&id=aaa
 *      - /xxxx/aa/bb => /app?link=xxx&id=aa&data=bb
 *
 * Donc les paramètres utilisés sont "link" pour la page pointée, "id" pour passer un premier paramètre à la page et "data" pour en passer un second
 */
public class ServletDispatcher extends HttpServlet {
    private CategoryDAO categoryDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        categoryDAO = DAOFactory.getInstance().getCategoryDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(req, resp);
        processRequest(request, response);
    }

    /***
     * Traite les requêtes et les rediriges vers le bon controlleur ou lance une page d'erreur
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.setAttribute("categories", categoryDAO.getCategories()); // For nav & multiples pages

        // for always have a valid cart in session
        if (request.getSession().getAttribute("cart") == null)
            request.getSession().setAttribute("cart", new Cart());

        String id = request.getParameter("link");
        if (id == null || id.equals(""))
            id = "home";
        request.setAttribute("pageId", id);


        try {
            // Tricky logic to instantiate the controller associated with the link param (automatic controllers are in the "controllers" package)
            // Exemple: /home => HomeController; /sign-in => SignInController
            Controller controller;
            try {
                Constructor<?> c = Class.forName(getController(id)).getConstructor(HttpServletRequest.class, HttpServletResponse.class);
                controller = (Controller) c.newInstance(request, response);
            } catch (Exception e) {
                // show 404 if no controller
                throw new ErrorManager(ErrorManager.ErrorTypes.ERROR_404);
            }

            controller.process();
            controller.render();
        } catch (ErrorManager e) {
            // If error in proccess show the clean error page
            ErrorController errorPage = new ErrorController(request, response, e);

            errorPage.process();
        } catch (Exception e) {
            ErrorController errorPage = new ErrorController(request, response, new ErrorManager(ErrorManager.ErrorTypes.UNKNOWN_ERROR));

            errorPage.process();
        }
    }

    /**
     * Permet de convertir l'entrée (link) en nom de controlleur
     * Les controlleurs doivent respecter une ecriture CamelCase et doivent être composés du nom de page + "Controller"
     * @param s le lien de page a transformé
     * @return le lien du controlleur associé à l'entrée (ex: /home => com.pearmarket.app.servlets.controllers.HomeController)
     */
    static String getController(String s){
        String[] parts = s.split("-");
        StringBuilder camelCaseString = new StringBuilder();
        for (String part : parts){
            camelCaseString.append(toProperCase(part));
        }
        return "com.pearmarket.app.servlets.controllers." + camelCaseString + "Controller";
    }

    /**
     * Mettre en CamelCase
     * @param s
     * @return chaine en CamelCase
     */
    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }
}
