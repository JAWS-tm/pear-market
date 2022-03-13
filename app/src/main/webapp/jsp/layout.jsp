<jsp:useBean id="title" scope="request" type="java.lang.String"/>
<jsp:useBean id="whiteNavBar" scope="request" type="java.lang.Boolean"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title><c:out value="${title}" /></title>

    <c:forEach var="fileName" items="${styleFiles}" >
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/${fileName}.css" />
    </c:forEach>

    <script src="https://kit.fontawesome.com/1e6afd82c6.js" crossorigin="anonymous"></script>
    <link rel="icon" type="image/jpg" href="assets/img/logo.png" />
</head>
<body>
    <div class="container">
        <header>
            <nav class="nav-bar ${whiteNavBar ? "white-background" : null}">
                <div class="background"></div>
                <div class="shop-logo">
                    <a href="${pageContext.request.contextPath}/">
                        <img src="${pageContext.request.contextPath}/assets/img/logo.png" alt="logo marque" />
                        <h1>Pear Market</h1>
                    </a>
                </div>
                <div class="links-menu">
                    <ul>
                        <li class="link">
                            <a href="${pageContext.request.contextPath}/" class="${pageId == "home" ? "selected" : null}">Accueil</a>
                        </li>
                        <li class="link sub-menu-parent">
                            <a href="${pageContext.request.contextPath}/store" class="store-link ${pageId == "store" ? "selected" : null}">
                                Magasin
                                <i class="fa-solid fa-chevron-down"></i>
                            </a>
                            <ul class="sub-menu">
                                <li>
                                    <a href="${pageContext.request.contextPath}/category/1">Téléphones</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/category/2">Ordinateurs</a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/category/3">Télévisions</a>
                                </li>
                            </ul>
                        </li>

                        <c:choose>
                            <c:when test="${sessionScope.loggedUser == null}">
                                <li class="link">
                                    <a href="${pageContext.request.contextPath}/sign-in" class="${pageId == "sign-in" ? "selected" : null}">Connexion</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li class="link sub-menu-parent">
                                    <a href="${pageContext.request.contextPath}/account" class="store-link ${pageId == "account" ? "selected" : null}">
                                        Mon Compte
                                        <i class="fa-solid fa-chevron-down"></i>
                                    </a>
                                    <ul class="sub-menu">
                                        <li>
                                            <a href="${pageContext.request.contextPath}/account">Gestion du compte</a>
                                        </li>
                                        <li>
                                            <a href="${pageContext.request.contextPath}/disconnect" class="selected">Déconnexion</a>
                                        </li>
                                    </ul>
                                </li>
                            </c:otherwise>
                        </c:choose>


                        <li class="link">
                            <a href="${pageContext.request.contextPath}/cart" class="cart-link ${pageId == "cart" ? "selected" : null}">
                                <i class="fa-solid fa-cart-shopping"></i>
                            </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </header>


        <c:import url="${pageContent}" />

    </div>

    <footer>
        <div class="grid-information">
            <h2 class="information-title">Information</h2>
            <h2 class="information-title">Information</h2>
            <h2 class="information-title">Information</h2>

            <p class="information-description">
                Lorem, ipsum dolor sit amet consectetur adipisicing elit. Accusamus dolor, provident molestias minus
                maxime ullam libero illo atque cumque nam?
            </p>
            <p class="information-description">
                Lorem ipsum dolor sit amet consectetur, adipisicing elit. Asperiores deserunt officia recusandae a
                voluptate nulla molestias minus quibusdam facere minima!
            </p>
            <p class="information-description">
                Lorem ipsum dolor, sit amet consectetur adipisicing elit. Aliquid ex quas dolorum dolore illum vitae
                voluptatibus ad possimus cupiditate nisi.
            </p>
        </div>

        <ul>
            <li>
                <a href="#" target="_blank">
                    <i class="fab fa-twitter"></i>
                </a>
            </li>
            <li>
                <a href="#" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                </a>
            </li>
            <li>
                <a href="#" target="_blank">
                    <i class="fab fa-instagram"></i>
                </a>
            </li>
            <li>
                <a href="#" target="_blank">
                    <i class="fab fa-linkedin-in"></i>
                </a>
            </li>
            <li>
                <a href="mailto:contact@pear-shop.fr">
                    <i class="far fa-envelope"></i>
                </a>
            </li>
        </ul>
    </footer>
</body>
</html>
