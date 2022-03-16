<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:if test="${loggedUser.admin}">
    <jsp:useBean id="users" scope="request" type="java.util.ArrayList"/>
</c:if>

<section class="content-area">
    <h1 class="page-title">Mon compte</h1>

    <c:if test="${loggedUser.blocked}">
        <div class="forget-coupon error">
            <i class="fa-solid fa-circle-exclamation"></i>
            <p>Votre compte est actuellement bloqué, veuillez contacter un administrateur si vous souhaitez contester.</p>
        </div>
    </c:if>


    <div class="product-tabs">
        <ul class="tabs-list">
            <li class="tab ${selectedTab == "account-infos" ? "active" : null}" data-tab-name="account-infos">Gérer compte</li>
            <li class="tab ${selectedTab == "orders" ? "active" : null}" data-tab-name="orders">Mes commandes</li>

            <c:if test="${loggedUser.admin}">
                <li class="tab ${selectedTab == "admin-products" ? "active" : null}" data-tab-name="admin-products">Gérer les produits</li>
                <li class="tab ${selectedTab == "users-list" ? "active" : null}" data-tab-name="users-list">Liste utilisateurs</li>
                <li class="tab ${selectedTab == "manage-orders" ? "active" : null}" data-tab-name="manage-orders">Gerer les commandes</li>
            </c:if>
        </ul>
        <div class="tabs-content-list">

            <div class="tab-content" id="account-infos">
                <div class="customer-info-container" id="customer-info-firstname">
                    <span>Nom : </span>
                    <span class="text-modif">${loggedUser.name}</span>
                </div>
                <div class="customer-info-container" id="customer-info-name">
                    <span>Prénom : </span>
                    <span class="text-modif">${loggedUser.firstname}</span>
                </div>
                <div class="customer-info-container" id="customer-info-email">
                    <span>Email : </span>
                    <span class="text-modif">${loggedUser.email}</span>
                </div>
                <div class="customer-info-container" id="customer-info-adresse">
                    <span>Adresse : </span>
                    <span class="text-modif">${loggedUser.address == null ? "Vous n'avez pas encore renseigné d'adresse." :loggedUser.formattedAddress }</span>
                </div>
                <div class="customer-info-container" id="customer-info-phone">
                    <span>Télephone : </span>
                    <span class="text-modif">${loggedUser.phone == null ? "Vous n\'avez pas encore renseigné de numéro." : loggedUser.phone }</span>
                </div>

                <a href="#">Modifier</a>
            </div>

            <div class="tab-content" id="orders">
                <section class="orders-container">
                    <h2>Historique des commandes :</h2>
                    <span class="small-info">Cliquer sur l'ID pour voir la facture</span>

                    <table>
                        <tr class="tr-head">
                            <th id="order-id-thead">ID</th>
                            <th id="order-date-thead">Date de commande</th>
                            <th id="order-products-thead">Produits</th>
                            <th id="order-price-thead">Total</th>
                            <th id="order-state-thead">Statut</th>
                        </tr>

                        <c:forEach items="${orders}" var="order">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/invoice/${order.id}" target="_blank">${order.id}</a></td>

                                <jsp:useBean id="order" class="com.pearmarket.app.beans.elements.Order"></jsp:useBean>
                                <td>${order.formattedDate}</td>
                                <td>${order.getContentResume()}</td>
                                <fmt:setLocale value='en-US'/>
                                <fmt:formatNumber var="itemTotalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.total}" />
                                <td>${fn:replace(itemTotalPrice, ",", " ")}€</td>
                                <td>
                                    <c:choose>
                                        <c:when test="${order.state == 1}">Préparation en cours</c:when>
                                        <c:when test="${order.state == 2}">Livraison en cours</c:when>
                                        <c:when test="${order.state == 3}">Terminée</c:when>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>

                    </table>
                </section>
            </div>


            <c:if test="${loggedUser.admin}">
                <div class="tab-content" id="admin-products">
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
                                        <form method="POST">
                                            <input type="hidden" name="productId" value="${product.id}">
                                            <input type="hidden" name="action" value="changeQuantity">
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

                        <a href="${pageContext.request.contextPath}/edit-product" class="red-button">Ajouter un produit</a>
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
                                    <td>
                                        <div class="action-buttons" user-id="${user.email}">
                                            <a href="#" class="toggle-admin" is-admin="${user.admin}">${user.admin == true ?"Supprimer admin" : "Mettre admin"}</a>
                                            <a href="#" class="toggle-blocked" is-blocked="${user.blocked}">${user.blocked == true ?"Débloquer" : "Bloquer"}</a>
                                        </div>
                                        <form method="POST">
                                            <input type="hidden" name="userId" value="${user.email}">
                                            <input type="hidden" name="action" value="deleteUser">
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

                <div class="tab-content" id="manage-orders">
                    <section class="orders-container">
                        <h2>Gestion des commandes :</h2>
                        <span class="small-info">Cliquer sur l'ID pour voir la facture</span>
                        <table class="manage-orders">
                            <tr class="tr-head">
                                <th>ID</th>
                                <th>Client</th>
                                <th>Date de commande</th>
                                <th>Total</th>
                                <th>Statut</th>
                            </tr>

                            <c:forEach items="${allOrders}" var="order">
                                <tr>
                                    <td><a href="${pageContext.request.contextPath}/invoice/${order.id}" target="_blank">${order.id}</a></td>

                                    <td>${order.customer.firstname} ${order.customer.name}</td>
                                    <td>${order.formattedDate}</td>
                                    <fmt:setLocale value='en-US'/>
                                    <fmt:formatNumber var="itemTotalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.total}" />
                                    <td>${fn:replace(itemTotalPrice, ",", " ")}€</td>
                                    <td>
                                        <select class="select-state value-${order.state}" order-id="${order.id}">
                                            <option value="1" ${order.state == 1 ? "selected" : null}>Préparation en cours</option>
                                            <option value="2" ${order.state == 2 ? "selected" : null}>Livraison en cours</option>
                                            <option value="3" ${order.state == 3 ? "selected" : null}>Terminée</option>
                                        </select>
                                    </td>
                                </tr>
                            </c:forEach>

                        </table>
                    </section>
                </div>
            </c:if>

        </div>
    </div>

</section>

<script src="${pageContext.request.contextPath}/js/product.js"></script>
<script src="${pageContext.request.contextPath}/js/request.js"></script>
<script src="${pageContext.request.contextPath}/js/account.js"></script>
