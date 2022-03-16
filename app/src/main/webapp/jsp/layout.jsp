<jsp:useBean id="title" scope="request" type="java.lang.String"/>
<jsp:useBean id="whiteNavBar" scope="request" type="java.lang.Boolean"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="categories" scope="request" type="java.util.ArrayList"/>


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
    <link rel="icon" type="image/jpg" href="${pageContext.request.contextPath}/assets/img/logo.png" />

    <script src="https://kit.fontawesome.com/1e6afd82c6.js" crossorigin="anonymous"></script>
    <script>const ctx = "${pageContext.request.contextPath}"</script>

</head>
<body>
    <c:if test="${hasLayout}">
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
                                <c:forEach var="category" items="${categories}">
                                    <li>
                                        <a href="${pageContext.request.contextPath}/category/${category.id}">${category.name}</a>
                                    </li>
                                </c:forEach>
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
    </c:if>


        <c:import url="${pageContent}" />

    <c:if test="${hasLayout}">
    </div>

    <footer>
        <div class="grid-information">
            <h2 class="information-title">Coordonnées</h2>
            <h2 class="information-title">Navigation</h2>
            <h2 class="information-title">Qui sommes-nous</h2>

            <div class="information-description">
                <p>
                    Pear Market <br>
                    <a class="maps-link" href="https://www.google.com/maps/place/Apple+Champs-Élysées/@48.8724212,2.2990295,17z/data=!3m1!4b1!4m5!3m4!1s0x47e66fe5296f0f4b:0xc33d115f0e33b9d2!8m2!3d48.8724177!4d2.3012182" target="_blank">
                        114 Av. des Champs-Élysées, 75008 Paris<br></a>
                    France<br>
                </p>

            </div>

            <div class="information-description navigation-footer">
                <a href="${pageContext.request.contextPath}/home">Accueil</a>
                <a href="${pageContext.request.contextPath}/store">magasin</a>
                <a href="${pageContext.request.contextPath}/account">Mon compte</a>
            </div>

            <div class="information-description">
                <p>
                    Petite startup française qui se lance dans le commerce de produits de dernières technologies. <br>

                </p>
            </div>
        </div>

        <ul>
            <li>
                <a class="footer-a" href="#" target="_blank">
                    <i class="fab fa-twitter"></i>
                </a>
            </li>
            <li>
                <a class="footer-a" href="#" target="_blank">
                    <i class="fab fa-facebook-f"></i>
                </a>
            </li>
            <li>
                <a class="footer-a" href="#" target="_blank">
                    <i class="fab fa-instagram"></i>
                </a>
            </li>
            <li>
                <a class="footer-a" href="#" target="_blank">
                    <i class="fab fa-linkedin-in"></i>
                </a>
            </li>
            <li>
                <a class="footer-a" href="mailto:contact@pear-shop.fr">
                    <i class="far fa-envelope"></i>
                </a>
            </li>
        </ul>
    </footer>
    </c:if>
</body>
</html>
