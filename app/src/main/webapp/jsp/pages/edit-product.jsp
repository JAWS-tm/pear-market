
<jsp:useBean id="product" scope="request" type="com.pearmarket.app.beans.elements.Product"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section class="content-area">
	<section class="edit-product-container">
		<h1 class="page-title">${param["id"] == null? "Ajouter" : "Modifier"} un produit </h1>
		<div class="edit-product">
			<form method="POST" class="edit-product-form form">

				<label for="productName">Nom du produit</label>
				<input type="text" name="productName" id="productName" value="${product.name}" placeholder="ex: iphone 13" required />

				<label for="productCategory">Catégorie</label>
				<select name="productCategory" id="productCategory" required>
					<c:forEach var="category" items="${categories}">
					<option value="${category.id}">${category.name}</option>
					</c:forEach>
				</select>

				<label for="productPrice">Prix</label>
				<input type="text" name="productPrice" id="productPrice" value="${product.price}" placeholder="ex: 29.99" required />

				<label for="productQuantity">Quantité</label>
				<input type="text" name="productQuantity" id="productQuantity" value="${product.quantity}" placeholder="ex: 19" required />

				<label for="productDescription">Description</label>
				<textarea id="productDescription" name="productDescription" placeholder="ex: magnifique produit">${product.description}</textarea>

				<label for="productImage">Image</label>
				<input type="file" name="productImage" id="productImage" value="${product.imageSrc}" accept="image/png"/>

				<input type="submit" name="${param["id"] == null? "add" : "update"}" id="save" value="Enregistrer" class="red-button">
			</form>
		</div>
	</section>
</section>
