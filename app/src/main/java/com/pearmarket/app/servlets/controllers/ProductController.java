package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        int id;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            throw new ErrorManager(ErrorManager.ErrorTypes.INVALID_PARAMETER);
        }


        Product product = productDAO.getProductById(id);
        if (product == null)
            throw new ErrorManager(ErrorManager.ErrorTypes.NULL_OBJECT);

        request.setAttribute("product", product);
    }
}
