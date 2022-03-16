package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StoreController extends Controller {

    private final ProductDAO productDAO;

    public StoreController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/store.jsp");

        this.setTitle("Nos Produits");
        this.setStyleFiles(new String[] {"style", "products-list", "responsive"});

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() {

        request.setAttribute("products", productDAO.getProducts(true));

    }
}
