<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<section class="content-area">
	<section class="cart-container">
		<h1>Panier</h1>
		<table>
			<tr>
				<th id="cart-product-thead">Produit</th>
				<th id="cart-price-thead">Prix</th>
				<th id="cart-quantity-thead">Quantité</th>
				<th id="cart-subtotal-thead">Total</th>
			</tr>

			<c:forEach var="row" items="${cartProducts}">
				<tr class="cart-row">
					<td>
						<div class="cart-product-container">
							<i class="fa-solid fa-xmark delete-btn" data-product-id="${row.key.id}"></i>
							<img src="${pageContext.request.contextPath}/assets/img/uploaded/products/${row.key.imageSrc}" alt="" />
							<h3>${row.key.name}</h3>
						</div>
					</td>

					<td>${row.key.getFormattedPrice()}€</td>
					<td>
						<input type="number" value="${row.value}" min="1" max="99" />
					</td>
					<fmt:formatNumber var="totalPrice" type="number" minFractionDigits="2" maxFractionDigits="2" value="${row.key.price * row.value}" />
					<td>${totalPrice}€</td>
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
				<div class="cart-subtotals">
					<h3>Totals</h3>
					<h3>90.00€</h3>
				</div>

				<div class="cart-total">
					<h3>Total</h3>
					<h3>90.00€</h3>
				</div>

				<a href="${pageContext.request.contextPath}/checkout" id="paiement-btn" class="red-button">Proceder au paiement</a>
			</div>
		</div>
	</section>
</section>

<script>const ctx = "${pageContext.request.contextPath}"</script>
<script src="${pageContext.request.contextPath}/js/cart.js"></script>