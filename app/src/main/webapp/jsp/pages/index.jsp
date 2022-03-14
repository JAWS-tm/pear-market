<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="landing-content">
	<img src="${pageContext.request.contextPath}/assets/img/home-landing.jpg" alt="objets en ventes" />
	<h3>L'electronique à portée de main</h3>
	<h1>Incroyable variété de produits à partir de 150€</h1>
	<a class="red-button" href="${pageContext.request.contextPath}/store">Voir plus</a>
</section>

<section class="grid-about-products">
	<div class="about-products">
		<i class="fa-solid fa-mobile-button"></i>
		<div class="content">
			<h3>Grande collection</h3>
			<p>De nombreuses marques</p>
		</div>
	</div>
	<div class="about-products">
		<i class="fa-solid fa-box"></i>
		<div class="content">
			<h3>Livraison gratuite</h3>
			<p>Livraison gratuite dès 200€ de commande</p>
		</div>
	</div>
	<div class="about-products">
		<i class="fa-solid fa-arrows-rotate"></i>
		<div class="content">
			<h3>Remboursement intégral</h3>
			<p>Si le produit ne vous convient pas</p>
		</div>
	</div>
</section>

<section class="home-categories">
	<div class="card-categories-container">

		<jsp:useBean id="categories" scope="request" type="java.util.ArrayList"/>
		<c:forEach var="category" items="${categories}">
			<div class="card-categories">
				<div class="square-div category-image">
					<div class="square-content">
						<img src="${pageContext.request.contextPath}/assets/img/uploaded/categories/${category.imageSrc}" alt="" />
					</div>
				</div>

				<div>
					<h2>${category.name}</h2>
					<p>${category.description}</p>
					<a class="view-more-btn" href="${pageContext.request.contextPath}/category/${category.id}">Voir plus</a>
				</div>
			</div>
		</c:forEach>

	</div>
</section>

<section class="products-list">
	<h2>Produits populaires</h2>
	<p class="description">Les meilleurs ventes des 30 derniers jours.</p>

	<div class="products-container">

		<jsp:useBean id="productsList" scope="request" type="java.util.ArrayList"/>
		<c:forEach var="product" items="${productsList}">
			<div class="item-container">
				<!--<span class="on-sale">20%</span>-->

				<a href="${pageContext.request.contextPath}/product/${product.id}">
					<div class="square-div img-card">
						<div class="square-content">
							<img src="${pageContext.request.contextPath}/assets/img/uploaded/products/${product.imageSrc}" alt="" />
						</div>
					</div>
				</a>

				<a href="${pageContext.request.contextPath}/category/${product.category.id}">
					<span class="categorie-item">${product.category.name}</span>
				</a>
				<a href="${pageContext.request.contextPath}/product/${product.id}">
					<span class="name-item">${product.name}</span>
				</a>
				<span class="price-item">${product.getFormattedPrice()}€</span>
			</div>
		</c:forEach>

	</div>
</section>
