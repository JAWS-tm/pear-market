<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<nav class="breadcrumb-links">
		<a href="${pageContext.request.contextPath}">Home</a>
		/ ${categoryName}
	</nav>
	<h1>${categoryName}</h1>

	<div class="filter-bar">
		<p>Affichage de ${products.size()} résultats</p>
		<form action="" method="get">
			<select name="orderBy" class="order-by">
				<option value="default">Tri par défaut</option>
				<option value="popularity">Trier par popularité</option>
				<option value="rating">Trier par note</option>
				<option value="date">Trier par date</option>
				<option value="price-asc">Trier par prix: croissant</option>
				<option value="price-desc">Trier par prix: décroissant</option>
			</select>
		</form>
	</div>

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