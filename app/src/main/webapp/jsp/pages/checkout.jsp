<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


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
				<input type="text" placeholder="Coupon code" />
				<a href="#" class="red-button">APPLIQUER</a>
			</div>
		</div>

		<div class="paiement-checkout">
				<form class="form" action="" class="delivery-details">
					<h2>Informations de livraison</h2>
					<div class="name-row">
						<div class="name-elem">
							<label for="last-name">Nom</label>
							<input
								type="text"
								name="last-name"
								id="last-name"
								placeholder="Dupond"
								required
							/>
						</div>

						<div class="name-elem">
							<label for="first-name">Prénom</label>
							<input
								type="text"
								name="first-name"
								id="first-name"
								placeholder="Martin"
								required
							/>
						</div>
					</div>

					<label for="country-region">Pays/Région</label>
					<select name="country-region" id="country-region">Pays-Région</select>

					<label for="address">Adresse</label>
					<input type="text" name="address" id="address" placeholder="Numéro et non de Rue" required />

					<label for="zip-code">Code postal</label>
					<input type="text" name="zip-code" id="zip-code" placeholder="ex: 49000" required />

					<label for="city">Ville</label>
					<input type="text" name="city" id="city" placeholder="ex: Angers" required />

					<label for="phone">Numéro de téléphone</label>
					<input type="text" name="phone" id="phone" placeholder="ex: 0786228635" required />

					<label for="email">Adresse email</label>
					<input type="text" name="email" id="email" placeholder="ex: dupond.martin@gmail.com" required />
				</form>

			<div class="your-order">
				<h2>Votre commande</h2>
				<div class="checkout-paiement-recap">
					<span>Produit</span>
					<span>Prix</span>
				</div>
				<div class="checkout-paiement-recap" id="text-nom-produit">
					<div id="wrap-product-nb">
						<span>iphone23</span>
						<span>x2</span>
					</div>
					<span>0.99€</span>
				</div>
				<div class="checkout-paiement-recap">
					<span>Livraison</span>
					<span>5€</span>
				</div>
				<div class="checkout-paiement-recap">
					<span>Total</span>
					<span>2907€</span>
				</div>
				<div class="mode-paiement-container">
					<div class="mode-paiement">
						<input type="radio" name="logo" id="paiement-paypal" value="paypal">
						<label for="paiement-paypal"><i class="fa-brands fa-cc-paypal"></i></label>
					</div>
					<div class="mode-paiement">
						<input type="radio" name="logo" id="paiement-visa" value="paypal">
						<label for="paiement-visa"><i class="fa-brands fa-cc-visa"></i></label>
					</div>
					<div class="mode-paiement">
						<input type="radio" name="logo" id="paiement-mc" value="paypal">
						<label for="paiement-mc"><i class="fa-brands fa-cc-mastercard"></i></label>
					</div>
					<div class="mode-paiement">
						<input type="radio" name="logo" id="paiement-applepay" value="paypal">
						<label for="paiement-applepay"><i class="fa-brands fa-cc-apple-pay"></i></label>
					</div>
				</div>

				<a href="#" id="paiement-btn" class="red-button">Confirmer la commande</a>

			</div>

		</div>
	</section>
</section>
<script src="${pageContext.request.contextPath}/js/checkout.js" ></script>

