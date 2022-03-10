package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.HttpCookie;

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
    public void process() {

        if (request.getMethod().equals("POST"))
            processSignUp();

    }

    private void processSignUp() {
        String email, password, password_check, name, firstname, address;
        email = request.getParameter("email");
        password = request.getParameter("password");
        password_check = request.getParameter("password_check");
        name = request.getParameter("last-name");
        firstname = request.getParameter("first-name");

        if (email == null || password == null || password_check == null || name == null || firstname == null)
        {
            // champ manquant -> afficher un message sur le site
            System.out.println("champ manquant");
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
        {
            System.out.println("email invalide");
            return;
        }

        if (!password.equals(password_check)) {
            System.out.println("mots de passe correspondent pas");
            return;
        }

        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        newUser.setName(name);
        newUser.setFirstname(firstname);

        userDAO.createAccount(newUser);


    }
}
