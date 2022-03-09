<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<section class="form-container">
		<h1>Mon compte</h1>
		<h2>Connexion</h2>
		<form action="" method="get" class="form-account">
			<label for="id">Adresse email</label>
			<input type="text" name="id" id="id" placeholder="exemple: mon-email@gmail.com" required />

			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password" required />

			<div class="checkbox-container">
				<input type="checkbox" id="remember-account" name="remember-account" />
				<label for="remember-account">Se souvenir de moi</label>
			</div>

			<input type="submit" class="red-button" value="S'identifier" />

			<a href="#">Mot de pass oublié?</a>
		</form>
	</section>
</section>