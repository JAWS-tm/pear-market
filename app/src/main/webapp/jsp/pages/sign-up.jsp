<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
	<section class="form-container">
		<h1>Mon compte</h1>
		<h2>S'inscrire</h2>

		<form class="form" action="" method="POST" class="form-account">
			<c:if test="${alreadyCreated}">
				<p class="error-message">Cette adresse mail a déjà un compte associé veuillez vous connecter. </p>
			</c:if>

			<div class="name-row">
				<div class="name-elem">
					<label for="lastname">Nom</label>
					<input type="text" name="lastname" id="lastname" placeholder="Dupond" required value="${lastname}" />
				</div>

				<div class="name-elem">
					<label for="firstname">Prénom</label>
					<input type="text" name="firstname" id="firstname" placeholder="Martin" required value="${firstname}"/>
				</div>
			</div>

			<label for="email">Nom d'utilisateur ou adresse email</label>
			<input type="email" name="email" id="email" placeholder="exemple: monemail@gmail.com" class="${emailMatchesFail ? "input-fail" : null}" required value="${email}"/>
			<c:if test="${emailMatchesFail}">
				<p class="error-message">Veuillez rentrer une adresse mail valide (exemple@domain.com)</p>
			</c:if>

			<label for="password">Mot de passe</label>
			<input type="password" name="password" id="password" required value="${password}" />

			<label for="password">Confirmez le mot de passe</label>
			<input type="password" name="password_check" id="password_check" required class="${pwdCheckFailed ? "input-fail" : null}" value="${password_check}"/>
			<c:if test="${pwdCheckFailed}">
				<p class="error-message">Les mots de passes ne correspondent pas</p>
			</c:if>

			<div class="checkbox-container">
				<input type="checkbox" id="agree-cgu" name="remember-account" required/>
				<label for="agree-cgu">Accepter les CGU</label>
			</div>

			<input type="submit" class="red-button" value="S'inscrire" />
		</form>
	</section>
</section>