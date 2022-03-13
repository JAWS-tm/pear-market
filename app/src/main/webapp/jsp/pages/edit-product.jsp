<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<section class="content-area">
	<section class="edit-product-container">
		<h1 class="page-title">Ajouter un produit</h1>
		<div class="edit-product">
			<form action="POST" class="edit-product-form form" >

				<label for="productName">Nom du produit</label>
				<input type="text" name="productName" id="productName" placeholder="ex: iphone 13" required />

				<label for="productCategorie">Catégorie</label>
				<input type="text" name="productCategorie" id="productCategorie" placeholder="ex: Smartphone" required />

				<label for="productDescription">Description</label>
				<input type="text" name="productDescription" id="productDescription" placeholder="ex: Angers" required />

				<label for="productPrice">Prix</label>
				<input type="text" name="productPrice" id="productPrice" placeholder="ex: 29.99" required />

				<label for="productQuantity">Quantité</label>
				<input type="text" name="productQuantity" id="productQuantity" placeholder="ex: 19" required />

				<label for="productImage">Image</label>
				<input type="file" name="productImage" id="productImage" accept="image/png"/>
			</form>
		</div>
	</section>
</section>
