<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<section class="content-area">
    <section class="checkout-container">
        <h1 class="page-title">Vérifications</h1>

        <div class="forget-coupon">
            <i class="fa-solid fa-circle-info"></i>
            <p>Vous avez un coupon?</p>
            <a href="#" id="coupon-toggle">Cliquez ici pour rentrer votre code</a>
        </div>

        <div id="window-coupon">
            <p>Si vous avez un code, veuillez le saisir ici.</p>

            <div class="cart-coupon">
                <input type="text" placeholder="Coupon code"/>
                <a href="#" class="red-button">APPLIQUER</a>
            </div>
        </div>

        <div class="paiement-checkout">
            <form action="" class="form delivery-details">
                <h2>Informations de livraison</h2>
                <div class="name-row">
                    <div class="name-elem">
                        <label for="last-name">Nom</label>
                        <input type="text" id="last-name" placeholder="Dupond" required readonly value="${sessionScope.loggedUser.name}" />
                    </div>

                    <div class="name-elem">
                        <label for="first-name">Prénom</label>
                        <input type="text" id="first-name" placeholder="Martin" required readonly value="${sessionScope.loggedUser.firstname}"/>
                    </div>
                </div>

                <label for="country-region">Pays/Région *</label>
                <select name="country-region" id="country-region">
                    <option value="fr">France</option>
                    <option value="en">Angleterre</option>
                </select>

                <label for="address">Adresse *</label>
                <input type="text" name="address" id="address" placeholder="Numéro et nom de rue" required/>

                <label for="zip-code">Code postal *</label>
                <input type="text" name="zip-code" id="zip-code" placeholder="ex: 49000" required/>

                <label for="city">Ville *</label>
                <input type="text" name="city" id="city" placeholder="ex: Angers" required/>

                <label for="phone">Numéro de téléphone *</label>
                <input type="text" name="phone" id="phone" placeholder="ex: 0786228635" required/>

                <label for="email">Adresse email</label>
                <input type="text" id="email" placeholder="ex: dupond.martin@gmail.com" required readonly value="${sessionScope.loggedUser.email}"/>

                <span class="form-info">* : champ requis</span>
            </form>

            <div class="your-order">
                <h2>Votre commande</h2>
                <div class="checkout-paiement-recap">
                    <span>Produit</span>
                    <span>Prix</span>
                </div>

				<c:set var="cartTotal" scope="request" value="0"/>

				<jsp:useBean id="cart" scope="request" type="java.util.HashMap"/>
				<c:forEach var="row" items="${cart}">
					<div class="checkout-paiement-recap item-price">
						<div class="wrap-product-nb">
							<span>${row.key.name}</span>
							<span>x${row.value}</span>
						</div>

						<fmt:setLocale value='en-US'/>
						<fmt:formatNumber var="price" type="number" minFractionDigits="2" maxFractionDigits="2" value="${row.key.price * row.value}" />
						<span>${fn:replace(price, ",", " ")}€</span>

						<c:set var="cartTotal" value="${cartTotal + row.key.price * row.value}"/>
					</div>
				</c:forEach>

                <div class="checkout-paiement-recap">
                    <span>Livraison</span>
                    <c:choose>
                        <c:when test="${shippingFees != 0}">
                            <span>${shippingFees}€</span>
                        </c:when>
                        <c:otherwise>
                            <span>Gratuit</span>
                        </c:otherwise>
                    </c:choose>
                </div>
                <div class="checkout-paiement-recap">
                    <span>Total</span>
					<fmt:setLocale value='en-US'/>
					<fmt:formatNumber var="total" type="number" minFractionDigits="2" maxFractionDigits="2" value="${cartTotal + shippingFees}" />
					<span>${fn:replace(total, ",", " ")}€</span>
                </div>
                <div class="mode-paiement-container">
                    <div class="mode-paiement">
                        <input type="radio" name="payment-type" id="paiement-visa" value="visa" checked>
                        <label for="paiement-visa"><i class="fa-brands fa-cc-visa"></i></label>
                    </div>
                    <div class="mode-paiement">
                        <input type="radio" name="payment-type" id="paiement-mc" value="mastercard">
                        <label for="paiement-mc"><i class="fa-brands fa-cc-mastercard"></i></label>
                    </div>
                    <div class="mode-paiement">
                        <input type="radio" name="payment-type" id="paiement-paypal" value="paypal">
                        <label for="paiement-paypal"><i class="fa-brands fa-cc-paypal"></i></label>
                    </div>
                    <div class="mode-paiement">
                        <input type="radio" name="payment-type" id="paiement-applepay" value="apple-pay">
                        <label for="paiement-applepay"><i class="fa-brands fa-cc-apple-pay"></i></label>
                    </div>
                </div>

                <a href="#" id="paiement-btn" class="red-button">Confirmer la commande</a>

            </div>

        </div>
    </section>
</section>
<script src="${pageContext.request.contextPath}/js/checkout.js"></script>

