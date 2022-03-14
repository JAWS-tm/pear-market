package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.utils.Cart;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.utils.ErrorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductController extends Controller {
    private final ProductDAO productDAO;

    public ProductController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/product.jsp");

        this.setTitle("Produit");
        this.setStyleFiles(new String[] {"product","responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ErrorManager {
        int id = parseInt(request.getParameter("id"));

        Product product = productDAO.getProductById(id);
        if (product == null)
            throw new ErrorManager(ErrorManager.ErrorTypes.NULL_OBJECT);

        request.setAttribute("product", product);


        if (request.getMethod().equals("POST") && request.getParameter("add-product") != null) {
            System.out.println("receive");
            int quantity = parseInt(request.getParameter("quantity"));

            Cart cart = (Cart) request.getSession().getAttribute("cart");
            cart.addProduct(id, quantity);

            System.out.println(cart.getComputedProducts());
        }


    }
}
