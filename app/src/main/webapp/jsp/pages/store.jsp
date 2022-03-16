<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="landing-content small">
	<img src="assets/img/home-landing.jpg" alt="objets en ventes" />
	<h1 class="single">Magasin</h1>
</section>

<section class="products-list left-align">
	<h2>Produits en Vedette</h2>
	<div class="products-container">
		<c:forEach var="product" items="${products}">
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
