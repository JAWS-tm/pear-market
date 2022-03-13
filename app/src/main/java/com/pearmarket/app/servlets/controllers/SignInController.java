package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignInController extends Controller {
    UserDAO userDAO;

    public SignInController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/sign-in.jsp");

        this.setTitle("Connexion");
        this.setStyleFiles(new String[] {"connection","responsive"});
        this.setWhiteNavBar(true);

        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws IOException, ServletException, ErrorManager {
        if (request.getSession().getAttribute("loggedUser") != null)
            this.redirect("/");

        if (request.getMethod().equals("POST"))
            processSignIn();
    }

    public void processSignIn() throws ServletException, ErrorManager, IOException {
        String email, password, rememberMe;
        email = request.getParameter("email");
        password = request.getParameter("password");
        rememberMe = request.getParameter("remember-account");

        System.out.println(request.getParameter("remember-account"));

        if (email == null || password == null)
        {
            request.setAttribute("loginError", "Veuillez remplir tous les champs");
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
        {
            request.setAttribute("loginError", "Veuillez rentrer une adresse mail valide (exemple@domain.com)");
            request.setAttribute("emailMatchesFail", true);
            return;
        }

        User user;
        if ((user = userDAO.tryConnect(email, password)) != null) {
            request.getSession().setAttribute("loggedUser", user);
            if (rememberMe != null)
            {
                // Créer un id de connexion unique chiffré dans un bdd et check
                // Cookie connexion = new Cookie("permanentConnexion", user.getEmail());
            }

            this.redirect("/");
        }
        else{
            request.setAttribute("loginError", "Impossible de se connecter à ce compte, vérifiez vos identifiants.");

        }
    }
}
