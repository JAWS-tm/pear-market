<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<section class="form-container">
		<h1>Mon compte</h1>
		<h2>S'inscrire</h2>

		<form action="" method="get" class="form-account">
			<div class="name-row">
				<div class="name-elem">
					<label for="last-name">Nom</label>
					<input type="text" name="last-name" id="last-name" placeholder="Dupond" required />
				</div>

				<div class="name-elem">
					<label for="first-name">Prénom</label>
					<input type="text" name="first-name" id="first-name" placeholder="Martin" required />
				</div>
			</div>

			<label for="email">Nom d'utilisateur ou adresse email</label>
			<input type="text" name="email" id="email" placeholder="exemple: monemail@gmail.com" required />

			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password" required />

			<label for="password">Confirmez le mot de passe</label>
			<input type="password" name="password_check" id="password_check" required />

			<div class="checkbox-container">
				<input type="checkbox" id="remember-account" name="remember-account" required />
				<label for="remember-account">Accepter les CGU</label>
			</div>

			<input type="submit" class="red-button" value="S'inscrire" />
		</form>
	</section>
</section>