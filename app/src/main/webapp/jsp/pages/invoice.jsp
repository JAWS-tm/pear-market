<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="order" scope="request" type="com.pearmarket.app.beans.elements.Order"/>

<div class="invoice-content">
    <header>
        <h1>Facture</h1>
        <div class="logo-container">
            <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="">
            <h2>Pear Market</h2>
        </div>
    </header>

    <section class="grid-invoice-container">
        <div class="grid-invoice">
            <div class="customer">
                <h3>Client:</h3>
                <span>${order.customer.name} ${order.customer.firstname}</span>
                <p>${fn:replace(order.customer.address, "\\", ", ")}</p>
            </div>

            <div class="seller">
                <h3>Vendeur:</h3>
                <span>Pear Market</span>
                <p>231 rue des Champs-Élysées 78000 Paris</p>
                <span>France</span>
            </div>

            <div class="info-delivery">
                <h3>Livraison:</h3>
                <p>${fn:replace(order.customer.address, "\\", ", ")}</p>
                <p>Prix : ${order.shippingFees == 0 ? "Offert" : order.shippingFees}</p>
            </div>

            <div class="info-invoice-container">
                <h3>Détails de Facture:</h3>
                <div class="info-invoice-left">
                    <span>Date de commande:</span>
                    <span>Numéro de commande:</span>
                    <span>Code promo :</span>
                </div>
                <div class="info-invoice-right">
                    <span>${order.formattedDate}</span>
                    <span>#${order.id}</span>
                    <span>${order.discountCode == null ? "Aucun" : order.shippingFees}</span>
                </div>
            </div>
        </div>
    </section>

    <section class="table-container">
        <table>
            <tr>
                <th id="cart-product-thead">Produit</th>
                <th id="cart-price-thead">Prix</th>
                <th id="cart-quantity-thead">Quantité</th>
                <th id="cart-subtotal-thead">Total</th>
            </tr>

            <c:forEach var="row" items="${order.content}">
                <tr>
                    <td>${row.key.name}</td>
                    <td>${row.key.formattedPrice}€</td>
                    <td>x${row.value}</td>
                    <fmt:setLocale value='en-US'/>
                    <fmt:formatNumber var="itemTotalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${row.key.price * row.value}" />
                    <td>${fn:replace(itemTotalPrice, ",", " ")}€</td>
                </tr>
            </c:forEach>
        </table>
    </section>

    <section class="recap-paiement">
        <fmt:setLocale value='en-US'/>
        <fmt:formatNumber var="orderTotal" type="number" minFractionDigits="2" maxFractionDigits="2" value="${order.total}" />
        <p>Montant de la Facture: ${fn:replace(orderTotal, ",", " ")}€</p>
        <p>
            <c:choose>
                <c:when test="${order.state == 1}">Préparation en cours</c:when>
                <c:when test="${order.state == 2}">Livraison en cours</c:when>
                <c:when test="${order.state == 3}">Terminée</c:when>
            </c:choose>
        </p>
        <div class="moyen-paiement">
            <p>Moyen de paiement : Carte bancaire</p>
            <p>Numéro de carte: *************1792</p>
        </div>
    </section>
</div>
