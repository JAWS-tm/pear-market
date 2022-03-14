package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.OrderDAO;
import com.pearmarket.app.beans.UserDAO;
import com.pearmarket.app.utils.Cart;
import com.pearmarket.app.beans.elements.Order;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckoutController extends Controller {
    UserDAO userDAO;
    OrderDAO orderDAO;

    public CheckoutController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/checkout.jsp");

        this.setTitle("Paiement");
        this.setStyleFiles(new String[] {"checkout","responsive"});
        this.setWhiteNavBar(true);

        userDAO = daoFactory.getUserDAO(DAOFactory.DBType.MariaDB);
        orderDAO = daoFactory.getOrderDAO(DAOFactory.DBType.MariaDB);
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


        int shippingFees;
        if (cart.getTotalPrice() >= 200)
            shippingFees = 0;
        else
            shippingFees = 10;
        request.setAttribute("shippingFees", shippingFees);


        if (request.getMethod().equals("POST")){
            if(handleForm(user)) {
                Order order = new Order(cart, user.getEmail(), shippingFees);

                int orderId = orderDAO.createOrder(order);
                if (orderId != -1)
                    redirect("/invoice/"+orderId);
                else
                    request.setAttribute("formError", "Une erreur s'est produite lors de la création de la commande, veuillez-réessayer.");

            }
        }
    }

    private boolean handleForm(User user) {
        String country, address, zipCode, city, phone;

        country = request.getParameter("country-region");
        address = request.getParameter("address");
        zipCode = request.getParameter("zip-code");
        city = request.getParameter("city");
        phone = request.getParameter("phone");

        // Check if not empty
        if (
                (country == null || country.isEmpty()) ||
                (address == null || address.isEmpty()) ||
                (zipCode == null || zipCode.isEmpty()) ||
                (city == null || city.isEmpty()) ||
                (phone == null || phone.isEmpty())
        ) {
            request.setAttribute("formError", "Veuillez remplir tous les champs");
            return false;
        }

        phone = phone.replaceAll(" ", "");
        if (!phone.matches("^((\\+)33|0|0033)[1-9](\\d{2}){4}$"))
        {
            request.setAttribute("formError", "Veuillez rentrer un numéro de téléphone valide.");
            request.setAttribute("phoneMatchesFail", true);
            return false;
        }

        String fullAddress = address + "\\" + city + "\\" + zipCode + "\\" + country;

        userDAO.changePhone(user.getEmail(), phone);
        userDAO.changeAddress(user.getEmail(), fullAddress);

        System.out.println("update user");
        return true;

    }

}
