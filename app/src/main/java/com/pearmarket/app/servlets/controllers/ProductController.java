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


        this.setStyleFiles(new String[] {"product","responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ErrorManager {
        int id = parseIntParam(request.getParameter("id"));

        Product product = productDAO.getProductById(id);
        if (product == null)
            throw new ErrorManager(ErrorManager.ErrorTypes.NULL_OBJECT);


        this.setTitle(product.getName());
        request.setAttribute("product", product);
        request.setAttribute("relatedProducts", productDAO.getProductsByCategory(product.getCategory().getId(), 4 ));


        if (request.getMethod().equals("POST") && request.getParameter("add-product") != null) {
            int quantity = parseIntParam(request.getParameter("quantity"));

            if (product.getQuantity() - quantity >= 0) {
                Cart cart = (Cart) request.getSession().getAttribute("cart");
                cart.addProduct(id, quantity);

                request.setAttribute("cartResponse", "\""+product.getName()+ "\" x" + quantity + " ajouté au panier" );
            } else
                request.setAttribute("cartResponse", "Le produit n'est plus en stock !");

        }
    }
}
