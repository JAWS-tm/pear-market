package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.OrderDAO;
import com.pearmarket.app.beans.elements.Order;
import com.pearmarket.app.beans.elements.User;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.utils.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class InvoiceController extends Controller {
    OrderDAO orderDAO;

    public InvoiceController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/invoice.jsp");
        this.setHasLayout(false);

        this.setStyleFiles(new String[] {"invoice","responsive"});
        this.setWhiteNavBar(true);

        orderDAO = daoFactory.getOrderDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException, ErrorManager {
        int orderId = parseIntParam(request.getParameter("id"));

        User user = getLoggedUser();
        if (user == null) {
            this.setRequestedLink("/invoice/"+orderId);
            this.redirect("/sign-in");
            return;
        }

        Order order = orderDAO.getOrder(orderId);
        if (order == null)
            throw new ErrorManager(ErrorManager.ErrorTypes.NULL_OBJECT);

        if (!order.getCustomer().getEmail().equals(user.getEmail()) && !user.getAdmin()) {
            redirect("/");
            return;
        }

        this.setTitle("Facture #"+orderId);
        request.setAttribute("order", order);

    }
}
