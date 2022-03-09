<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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

			<tr>
				<td>
					<div class="cart-product-container">
						<i class="fa-solid fa-xmark"></i>
						<img src="assets/img/phone.png" alt="" />
						<h3>Nom</h3>
					</div>
				</td>
				<td>prix</td>
				<td>
					<input type="number" value="1" min="1" max="99" />
				</td>
				<td>prix total</td>
			</tr>
		</table>
		<div class="cart-coupon">
			<input type="text" placeholder="Coupon code" />
			<a href="#" class="red-button">APPLIQUER</a>
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

				<a href="/checkout.html" id="paiement-btn" class="red-button">Proceder au paiement</a>
			</div>
		</div>
	</section>
</section>