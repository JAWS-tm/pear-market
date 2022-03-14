package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.beans.elements.Cart;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutController extends Controller {
    UserDAO userDAO;

    public CheckoutController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/checkout.jsp");

        this.setTitle("Paiement");
        this.setStyleFiles(new String[] {"checkout","responsive"});
        this.setWhiteNavBar(true);

        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {
        User user = getLoggedUser();
        if (user == null) {
            this.setRequestedLink("/checkout");
            this.redirect("/sign-in");
            return;
        }


        Cart cart = (Cart) request.getSession().getAttribute("cart");
        if (cart == null) {
            redirect("/cart");
            return;
        }
        request.setAttribute("cart", cart.getComputedProducts());


        if (cart.getTotalPrice() >= 200)
            request.setAttribute("shippingFees", 0);
        else
            request.setAttribute("shippingFees", 10);





        if (request.getMethod().equals("POST"))
            handleForm(user);
    }

    private void handleForm(User user) {
        String country, address, zipCode, city, phone;

        country = request.getParameter("country-region");
        address = request.getParameter("address");
        zipCode = request.getParameter("zip-code");
        city = request.getParameter("city");
        phone = request.getParameter("phone");

        if (country == null || address == null || zipCode == null || city == null || phone == null) {
            request.setAttribute("formError", "Veuillez remplir tous les champs");
            return;
        }

        phone = phone.replaceAll(" ", "");
        if (!phone.matches("^((\\+)33|0|0033)[1-9](\\d{2}){4}$"))
        {
            request.setAttribute("formError", "Veuillez rentrer un numéro de téléphone valide.");
            request.setAttribute("phoneMatchesFail", true);
            return;
        }

        String fullAddress = address + "\\" + city + "\\" + zipCode + "\\" + country;

        userDAO.changePhone(user.getEmail(), phone);
        userDAO.changeAddress(user.getEmail(), address);


    }

}
