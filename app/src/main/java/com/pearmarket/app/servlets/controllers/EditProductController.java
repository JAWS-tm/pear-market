package com.pearmarket.app.servlets.controllers;

import com.pearmarket.app.beans.CategoryDAO;
import com.pearmarket.app.beans.DAOFactory;
import com.pearmarket.app.beans.ProductDAO;
import com.pearmarket.app.beans.elements.Category;
import com.pearmarket.app.beans.elements.Product;
import com.pearmarket.app.servlets.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditProductController extends Controller {

    final ProductDAO productDAO;
    final CategoryDAO categoryDAO;
    public EditProductController(HttpServletRequest request, HttpServletResponse response){
        super(request, response);

        this.setJspLink("/jsp/pages/edit-product.jsp");

        this.setTitle("edit product");
        this.setStyleFiles(new String[] {"edit-product", "responsive"});
        this.setWhiteNavBar(true);

        productDAO = daoFactory.getProductDAO(DAOFactory.DBType.MariaDB);
        categoryDAO = daoFactory.getCategoryDAO(DAOFactory.DBType.MariaDB);
    }

    @Override
    public void process() throws ServletException, IOException {

        String productId = request.getParameter("id");




        if (request.getMethod().equals("POST")){

            Product product = new Product();
            Category newCategorie = new Category();

            String productName = request.getParameter("productName");
            String productCategory = request.getParameter("productCategory");
            String productPrice = request.getParameter("productPrice");
            String productQuantity = request.getParameter("productQuantity");
            String productDescription = request.getParameter("productDescription");
            String productImage = request.getParameter("productImage");


            System.out.println(productName);
            System.out.println(productCategory);
            System.out.println(productPrice);
            System.out.println(productQuantity);

            System.out.println(productImage);

            if (productName != null || productCategory != null || productPrice != null || productQuantity != null) {
                newCategorie.setId(Integer.parseInt(productCategory));
                product.setCategory(newCategorie);

                product.setId(Integer.parseInt(productId));
                product.setName(productName);
                product.setPrice(Float.parseFloat(productPrice));
                product.setQuantity(Integer.parseInt(productQuantity));
                product.setDescription(productDescription);
                if (productImage != null) {
                    product.setImageSrc(productImage);
                    //Part imageFile = request.getPart(productImage); // "file" ?



                if (request.getParameter("add") != null) {
                    productDAO.addProduct(product);
                    System.out.println("on ajoute un produit");
                }
                else if (request.getParameter("update") != null) {
                    productDAO.updateProduct(product);
                    System.out.println("on edit un produit");
                }
                redirect("/account");
            }
            else
                System.out.println("il manque un champ lors de la mise à jour d'un produit");
        }


        request.setAttribute("categories", categoryDAO.getCategories());

        if (productId != null) {
            request.setAttribute("product", productDAO.getProductById(Integer.parseInt(productId)));
        }
        else
            request.setAttribute("product", new Product());
    }
}
