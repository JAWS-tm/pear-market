<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${product != null}">
	<jsp:useBean id="product" scope="request" type="com.pearmarket.app.beans.elements.Product"/>
</c:if>

<section class="content-area">
	<section class="edit-product-container">
		<h1 class="page-title">${param["id"] == null? "Ajouter" : "Modifier"} un produit </h1>
		<div class="edit-product">
			<form method="POST" class="edit-product-form form" >
				<c:if test="${formError != null}">
					<p class="error-banner">${formError}</p>
				</c:if>

				<label for="productName">Nom du produit</label>
				<input type="text" name="name" id="productName" value="${param.name != null ? param.name : (product != null ? product.name : null)}" placeholder="ex: iphone 13" required />

				<label for="productCategory">Catégorie</label>
				<select name="category" id="productCategory" required>
					<option value="-1">Sélectionnez</option>
					<c:forEach var="category" items="${categories}">
					<option value="${category.id}" ${param.category == category.id || (product != null && product.category.id == category.id) ? "selected" : null}>${category.name}</option>
					</c:forEach>
				</select>

				<label for="productPrice">Prix</label>
				<input type="text" name="price" id="productPrice" value="${param.price != null ? param.price : (product != null ? product.price : null)}" placeholder="ex: 29.99" required />

				<label for="productQuantity">Quantité</label>
				<input type="text" name="quantity" id="productQuantity" value="${param.quantity != null ? param.quantity : (product != null ? product.quantity : null)}" placeholder="ex: 19" required />

				<label for="productDescription">Description</label>
				<textarea id="productDescription" name="description" placeholder="ex: magnifique produit">${param.description != null ? param.description : (product != null ? product.description : null)}</textarea>

<%--				<label for="productImage">Image <span class="small-info">(stockée dans uploaded/products)</span></label>--%>
<%--				<input type="text" name="image" id="productImage" value="${product != null ? product.imageSrc : null}" placeholder="phone.png">--%>
<%--			Pas le temps pour le moment de gérer les multipart	<input type="file" name="image" id="productImage" value="${product != null ? product.imageSrc : null}" accept="image/png"/>--%>

				<label for="productImage">Images disponibles (mettre dans le fichier uploaded/products pour en ajouter)</label>
				<select name="image" id="productImage" required>
					<option>Sélectionnez</option>
					<c:forEach var="image" items="${availableImages}">
						<option value="${image}" ${product != null ? (product.imageSrc == image ? "selected" : null) : null}>${image}</option>
					</c:forEach>
				</select>

				<input type="submit" name="${param["id"] == null? "add" : "update"}" id="save" value="Enregistrer" class="red-button">
			</form>
		</div>
	</section>
</section>
