<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section class="content-area">
	<section class="cart-container">
		<h1>Panier</h1>

        <c:if test="${loggedUser.blocked}">
            <div class="forget-coupon error">
                <i class="fa-solid fa-circle-exclamation"></i>
                <p>Votre compte est actuellement bloqué, les commandes sont donc désactivées.</p>
            </div>
        </c:if>


        <div class="forget-coupon error invisible">
            <i class="fa-solid fa-circle-exclamation"></i>
            <p>Une erreur s'est produite lors de la modification du panier. Veuillez reessayer</p>
        </div>

        <jsp:useBean id="cartProducts" scope="request" type="java.util.LinkedHashMap"/>
        <c:if test="${not empty cartProducts}">
            <c:set var="cartTotal" scope="request" value="0"/>
            <div id="cart-product-list">
                <table>
                    <tr>
                        <th id="cart-product-thead">Produit</th>
                        <th id="cart-price-thead">Prix</th>
                        <th id="cart-quantity-thead">Quantité</th>
                        <th id="cart-subtotal-thead">Total</th>
                    </tr>

                    <c:forEach var="row" items="${cartProducts}">
                        <tr class="cart-row" data-product-id="${row.key.id}">
                            <td>
                                <div class="cart-product-container">
                                    <i class="fa-solid fa-xmark cross-delete-btn delete-btn"></i>
                                    <img src="${pageContext.request.contextPath}/assets/img/uploaded/products/${row.key.imageSrc}" alt="" />
                                    <h3>${row.key.name}</h3>
                                </div>
                            </td>

                            <td>${row.key.getFormattedPrice()}€</td>

                            <td>
                                <div class="change-quantity-container">
                                    <input type="number" name="newQuantity" class="newQuantity" value="${row.value}" min="0" last-value="${row.value}">
                                    <div class="applyChange-container">
                                        <button class="applyChange changeQuantity" type="submit" name="applyChange" value="">
                                            <i class="fa-solid fa-check"></i>
                                        </button>
                                    </div>
                                </div>

                            </td>
                            <fmt:setLocale value='en-US'/>
                            <fmt:formatNumber var="itemTotalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${row.key.price * row.value}" />
                            <td class="row-total" item-price="${row.key.price}">${fn:replace(itemTotalPrice, ",", " ")}€</td>

                            <c:set var="cartTotal" value="${cartTotal + row.key.price * row.value}"/>
                        </tr>
                    </c:forEach>

                </table>

                <div class="cart-coupon">
                    <input type="text" placeholder="Coupon code" />
                    <a href="#" class="red-button">Appliquer</a>
                </div>

                <div class="cart-totals-price">
                    <div class="cart-totals-text-container">
                        <h2>Total panier</h2>
                    </div>
                    <div class="cart-total-flex-container">
<%--                        <div class="cart-subtotals">--%>
<%--                            <h3>Totals</h3>--%>
<%--                            <h3>90.00€</h3>--%>
<%--                        </div>--%>

                        <div class="cart-total">
                            <h3>Total</h3>
                            <fmt:setLocale value='en-US'/>
                            <fmt:formatNumber var="totalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${cartTotal}" />
                            <h3 id="total-price">${fn:replace(totalPrice, ",", " ")}€</h3>
                        </div>

                        <a href="${pageContext.request.contextPath}/checkout" id="paiement-btn" class="red-button">Proceder au paiement</a>
                    </div>
                </div>
            </div>
        </c:if>

        <div id="no-products" class="${not empty cartProducts ? "invisible" : ""}">
            <div class="forget-coupon">
                <i class="fa-solid fa-circle-info"></i>
                <p>Votre panier est vide</p>
            </div>
            <a href="${pageContext.request.contextPath}/" class="red-button">Retourner à l'accueil</a>
        </div>

	</section>
</section>

<script src="${pageContext.request.contextPath}/js/request.js"></script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>
<script src="${pageContext.request.contextPath}/js/account.js"></script>