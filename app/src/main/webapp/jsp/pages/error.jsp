<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<title>Mon compte</title>
		<link rel="stylesheet" href="css/account.css" />
		<link rel="stylesheet" href="css/responsive.css" />
		<script src="https://kit.fontawesome.com/1e6afd82c6.js" crossorigin="anonymous"></script>
		<link rel="icon" type="image/jpg" href="assets/img/logo.png" />
	</head>
	<body>
		<div class="container">
			<header>
				<nav class="nav-bar white-background">
					<div class="background"></div>
					<div class="shop-logo">
						<a href="/">
							<img src="assets/img/logo2.svg" alt="logo marque" />
							<h1>Pear Vendor</h1>
						</a>
					</div>
					<div class="links-menu">
						<ul>
							<li class="link">
								<a href="/" class="selected">Accueil</a>
							</li>
							<li class="link sub-menu-parent">
								<a href="/store.html" class="store-link">
									Magasin
									<i class="fa-solid fa-chevron-down"></i>
								</a>
								<ul class="sub-menu">
									<li>
										<a href="/category.html">Téléphones</a>
									</li>
									<li>
										<a href="/category.html">Ordinateurs</a>
									</li>
									<li>
										<a href="/category.html">Télévisions</a>
									</li>
								</ul>
							</li>
							<li class="link">
								<a href="/jsp/pages/account.html">Mon compte</a>
							</li>
							<li class="link">
								<a href="/cart.html" class="cart-link">
									<i class="fa-solid fa-cart-shopping"></i>
								</a>
							</li>
						</ul>
					</div>
				</nav>
			</header>

			<section class="content-area">
				<div class="error-container">
					<div class="error-message">
						<h1>Une erreur est survenue</h1>
						<p>Vérifiez le lien, l'article est introuvable, etc</p>
					</div>
					<img src="assets/img/error.png" class="error-image" alt="une erreur" />
				</div>
			</section>
		</div>
	</body>
</html>
