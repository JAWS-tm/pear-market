<jsp:useBean id="product" scope="request" type="com.pearmarket.app.beans.elements.Product"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<div class="product-flex">
		<div class="square-div img-container">
			<div class="square-content">
				<img src="${pageContext.request.contextPath}/assets/img/uploaded/products/${product.imageSrc}" alt="" />
			</div>
		</div>
		<div class="product-info">
			<nav class="breadcrumb-links">
				<a href="${pageContext.request.contextPath}">Home</a>
				/
				<a href="${pageContext.request.contextPath}/category/${product.category.id}">${product.category.name}</a>
				/ Iphone 12
			</nav>
			<h1>${product.name}</h1>
			<span class="price">${product.formattedPrice}€</span>
			<p>
				${product.description}
			</p>
			<div class="add_product">
				<form action="" method="post">
					<input class="quantity" type="number" value="1" min="1" max="99" name="quantity"/>
					<input type="submit" class="red-button" value="Ajouter au panier" name="add-product"/>
				</form>
			</div>

			<div class="more_meta">
				<span>
					Categorie:
					<a href="add-to-cart">${product.category.name}</a>
				</span>
			</div>
		</div>
	</div>
	<div class="product-tabs">
		<ul class="tabs-list">
			<li class="tab active" data-tab-name="description">Description</li>
			<li class="tab" data-tab-name="comments">Commentaires (0)</li>
		</ul>
		<div class="tabs-content-list">
			<div class="tab-content active" id="description">
				${product.description}
				${product.attributes}
			</div>
			<div class="tab-content" id="comments">
				<div class="rewiews-container">
					<h3>Commentaires</h3>
					<p>Pas encore de commentaires</p>
				</div>

				<form class="review-form">
					<h3>Ajouter un avis</h3>
					<label for="rating-input">Votre note</label>
					<div class="rating">
						<span>
							<a href="" data-value="5"></a>
							<a href="" data-value="4"></a>
							<a href="" data-value="3"></a>
							<a href="" data-value="2"></a>
							<a href="" data-value="1"></a>
						</span>
					</div>
					<input type="hidden" name="rating" id="rating-input" required />

					<label for="comment">Votre commentaire</label>
					<textarea name="" id="comment" cols="30" rows="10" required></textarea>

					<!-- <label for="name">Nom</label> -->
					<input type="submit" value="Envoyer" class="red-button" />
				</form>
			</div>
		</div>

		<section class="related-products products-list left-align">
			<h2>Produits similaires</h2>
			<div class="products-container">
				<div class="item-container">
					<a href="">
						<div class="img-item-container">
							<img src="assets/img/phone.png" alt="" />
						</div>
					</a>

					<span class="categorie-item">Téléphone</span>
					<a href="">
						<span class="name-item">Iphone 12</span>
					</a>
					<span class="price-item">55€</span>
				</div>
				<div class="item-container">
					<a href="">
						<div class="img-item-container">
							<img src="assets/img/phone.png" alt="" />
						</div>
					</a>

					<span class="categorie-item">Téléphone</span>
					<a href="">
						<span class="name-item">Iphone 12</span>
					</a>
					<span class="price-item">55€</span>
				</div>
			</div>
		</section>
	</div>
</section>
<script src="${pageContext.request.contextPath}/js/product.js"></script>
