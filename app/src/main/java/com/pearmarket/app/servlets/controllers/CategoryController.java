package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.servlets.Controller;
import com.pearmarket.app.servlets.ErrorManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoryController extends Controller {
    final ProductDAO productDAO;
    final CategoryDAO categoryDAO;

    public CategoryController(HttpServletRequest request, HttpServletResponse response) {
        super(request, response);

        this.setJspLink("/jsp/pages/category.jsp");
        this.setTitle("Catégorie");
        this.setStyleFiles(new String[] {"category","responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        categoryDAO = daoFactory.getCategoryDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ErrorManager {
        int categoryId;
        try {
            categoryId = Integer.parseInt(request.getParameter("id")); // check if id exist
        } catch (NumberFormatException e) {
            throw new ErrorManager(ErrorManager.ErrorTypes.INVALID_PARAMETER);
        }


        Category cat = categoryDAO.getCategory(categoryId);
        if (cat == null)
            throw new ErrorManager(ErrorManager.ErrorTypes.NULL_OBJECT);

        request.setAttribute("category", cat);
        this.setTitle(cat.getName());

        request.setAttribute("products", productDAO.getProductsByCategory(categoryId));

    }
}
