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
    public void process() throws IOException {

        if (request.getMethod().equals("POST"))
            processSignUp();

    }

    private void processSignUp() throws IOException {
        String email, password, password_check, name, firstname, address;
        email = request.getParameter("email");
        password = request.getParameter("password");
        password_check = request.getParameter("password_check");
        name = request.getParameter("lastname");
        firstname = request.getParameter("firstname");

        // For keep input filled
        //request.setAttribute("formFilled", true);
        request.setAttribute("email", email);
        request.setAttribute("password", password);
        request.setAttribute("password_check", password_check);
        request.setAttribute("lastname", name);
        request.setAttribute("firstname", firstname);


        if (email == null || password == null || password_check == null || name == null || firstname == null)
        {
            System.out.println("champ manquant" + email + " " +password+ " "+password_check+ " "+name+ " "+firstname);
            return;
        }

        if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$"))
        {
            request.setAttribute("emailMatchesFail", true);
            return;
        }

        if (!password.equals(password_check)) {
            request.setAttribute("pwdCheckFailed", true);
            return;
        }


        User user = userDAO.createAccount(email, BCrypt.hashpw(password, BCrypt.gensalt()), name, firstname);
        if(user != null){
            System.out.println("gg t'as crée un compte");
            request.getSession().setAttribute("loggedUser", user);
            response.sendRedirect( request.getContextPath() );
        }
        else
            request.setAttribute("alreadyCreated", true);


    }
}
