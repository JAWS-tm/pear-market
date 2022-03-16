package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.utils.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SignUpController extends Controller {
    final UserDAO userDAO;

    public SignUpController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/sign-up.jsp");

        this.setTitle("Inscription");
        this.setStyleFiles(new String[] {"connection","responsive"});
        this.setWhiteNavBar(true);

        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws IOException, ServletException {
        if (getLoggedUser() != null) {
            this.redirect("/");
            return;
        }

        if (request.getMethod().equals("POST"))
            processSignUp();
    }

    /**
     * Gère et vérifie le formulaire et créer le compte de l'utilisateur si tous les champs sont bons
     * @throws IOException
     * @throws ServletException
     */
    private void processSignUp() throws IOException, ServletException {
        String email, password, password_check, name, firstname, address;
        email = request.getParameter("email");
        password = request.getParameter("password");
        password_check = request.getParameter("password_check");
        name = request.getParameter("lastname");
        firstname = request.getParameter("firstname");

        // For keep input filled
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("password_check", password_check);
        request.setAttribute("lastname", name);
        request.setAttribute("firstname", firstname);


        if (email == null || password == null || password_check == null || name == null || firstname == null)
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

        if (!password.equals(password_check)) {
            request.setAttribute("loginError", "Les mots de passes ne correspondent pas");
            request.setAttribute("pwdCheckFailed", true);
            return;
        }


        User user = userDAO.createAccount(email, password, name, firstname);
        if(user != null){
            setLoggedUser(user);

            this.redirect("/");
            return;
        }
        else
            request.setAttribute("loginError", "Cette adresse mail a déjà un compte associé veuillez vous connecter");

    }
}
