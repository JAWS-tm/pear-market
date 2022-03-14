<jsp:useBean id="users" scope="request" type="java.util.ArrayList"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
    <h1 class="page-title">Mon compte</h1>

    <div class="product-tabs">
        <ul class="tabs-list">
            <li class="tab active" data-tab-name="set-account">Gérer compte</li>
            <li class="tab" data-tab-name="orders">Mes commandes</li>
            <li class="tab" data-tab-name="products">Gérer les produits</li>
            <li class="tab" data-tab-name="users-list">Liste utilisateurs</li>
        </ul>
        <div class="tabs-content-list">

            <div class="tab-content active" id="set-account">
                <div class="customer-info-container" id="customer-info-firstname">
                    <span>Nom : </span>
                    <span class="text-modif">${user.name}</span>
                </div>
                <div class="customer-info-container" id="customer-info-name">
                    <span>Prénom : </span>
                    <span class="text-modif">${user.firstname}</span>
                </div>
                <div class="customer-info-container" id="customer-info-email">
                    <span>email : </span>
                    <span class="text-modif">${user.email}</span>
                </div>
                <div class="customer-info-container" id="customer-info-adresse">
                    <span>adresse : </span>
                    <span class="text-modif">${user.address == null ? "Vous n'avez pas encore renseigné d'adresse." :user.address }</span>
                </div>

                <a href="#">Modifier</a>
            </div>

            <div class="tab-content" id="orders">
                <section class="orders-container">
                    <h2>Historique des commandes :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="order-date-thead">Date de commande</th>
                            <th id="order-id-thead">N° de commande</th>
                            <th id="order-products-thead">Produits</th>
                            <th id="order-price-thead">Total</th>
                            <th id="order-state-thead">Statut</th>
                        </tr>

                        <tr>
                            <td>3 mars 2020</td>
                            <td><a href="#">827741596</a></td>
                            <td>Iphone39</td>
                            <td>0.99€</td>
                            <td>livrée</td>
                        </tr>

                        <tr>
                            <td>12 mars 2020</td>
                            <td><a href="#">85423</a></td>
                            <td>Iphone2</td>
                            <td>120.99€</td>
                            <td>en cours</td>
                        </tr>
                    </table>
                </section>
            </div>

            <div class="tab-content" id="products">
                <section class="gestion-products-container">
                    <h2>Produits en ligne :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="gestion-product-id-thead">Identifiant</th>
                            <th id="gestion-product-product-thead">Produit</th>
                            <th id="gestion-product-category-thead">Catégorie</th>
                            <th id="gestion-product-stock-thead">Stock</th>
                            <th id="gestion-product-action-thead">Action</th>
                        </tr>

                        <c:forEach var="product" items="${products}">
                        <tr>
                            <td>${product.id}</td>
                            <td>${product.name}</td>
                            <td>${product.category.name}</td>
                            <td>
                                <form method="POST" action="${pageContext.request.contextPath}/account/changeQuantity">
                                    <input type="hidden" name="productId" value="${product.id}">
                                    <input type="number" name="newQuantity" value="${product.quantity}" min="0">
                                    <div class="applyChange-container">
                                        <input class="applyChange" type="submit" name="applyChange" value="">
                                        <i class="fa-solid fa-check"></i>
                                    </div>
                                </form>
                            </td>
                            <td><div><a href="${pageContext.request.contextPath}/account/delete/${product.id}">Supprimer</a>
                                <a href="${pageContext.request.contextPath}/edit-product/${product.id}">Modifier</a></div></td>
                        </tr>
                        </c:forEach>
                    </table>

                    <a href="#" class="red-button">Ajouter un produit</a>
                </section>
            </div>

            <div class="tab-content" id="users-list">
                <section class="users-list-container">
                    <h2>Gestion de la liste d'utilisateurs :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="users-list-id-thead">E-mail</th>
                            <th id="users-list-firstname-thead">Nom</th>
                            <th id="users-list-name-thead">Prénom</th>
                            <th id="users-list-action-thead">Action</th>
                        </tr>
                        <c:forEach var="user" items="${users}">
                        <tr>
                            <td>${user.email}</td>
                            <td>${user.name}</td>
                            <td>${user.firstname}</td>
                            <td><div class="action-buttons"><a href="#">${user.admin == true ?"Supprimer admin" : "Mettre admin"}</a>  <a href="#">${user.blocked == true ?"Débloquer" : "Bloquer"}</a> </div>
                                <form method="POST" action="${pageContext.request.contextPath}/account/deleteUser">
                                    <input type="hidden" name="deleteUser" value="${user.email}">
                                    <div>
                                        <input class="applyChange cross-account z-index" type="submit" name="deleteUser" value="">
                                        <i class="fa-solid fa-xmark cross-delete-btn cross-account"></i>
                                    </div>
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                    </table>
                </section>
            </div>

        </div>
    </div>

</section>

<script src="${pageContext.request.contextPath}/js/product.js"></script>
<script src="${pageContext.request.contextPath}/js/account.js"></script>
