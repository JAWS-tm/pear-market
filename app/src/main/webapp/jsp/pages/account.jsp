<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<section class="content-area">
    <h1 class="page-title">Mon compte</h1>

    <div class="product-tabs">
        <ul class="tabs-list">
            <li class="tab active" data-tab-name="set-account">Gérer compte</li>
            <li class="tab" data-tab-name="orders">Mes commandes</li>
            <li class="tab" data-tab-name="products">Gérer les produits</li>
            <li class="tab" data-tab-name="black-list">Liste noire</li>
        </ul>
        <div class="tabs-content-list">

            <div class="tab-content active" id="set-account">
                <div class="customer-info-container" id="customer-info-firstname">
                    <span>Nom : </span>
                    <span class="text-modif">Dumartin</span>
                </div>
                <div class="customer-info-container" id="customer-info-name">
                    <span>Prénom : </span>
                    <span class="text-modif">Hugo</span>
                </div>
                <div class="customer-info-container" id="customer-info-email">
                    <span>email : </span>
                    <span class="text-modif">Hugo.dumartin@reseau.eseo.fr</span>
                </div>
                <div class="customer-info-container" id="customer-info-adresse">
                    <span>adresse : </span>
                    <span class="text-modif">8 rue jean charles de ses morts</span>
                </div>

                <a href="#">Modifier</a>
            </div>

            <div class="tab-content" id="orders">
                <section class="orders-container">
                    <h2>Historique des commandes :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="order-date-thead">Date de commande</th>
                            <th id="order-id-thead">N° de commande</th>
                            <th id="order-products-thead">Produits</th>
                            <th id="order-price-thead">Total</th>
                            <th id="order-state-thead">Statut</th>
                        </tr>

                        <tr>
                            <td>3 mars 2020</td>
                            <td><a href="#">827741596</a></td>
                            <td>Iphone39</td>
                            <td>0.99€</td>
                            <td>livrée</td>
                        </tr>

                        <tr>
                            <td>12 mars 2020</td>
                            <td><a href="#">85423</a></td>
                            <td>Iphone2</td>
                            <td>120.99€</td>
                            <td>en cours</td>
                        </tr>
                    </table>
                </section>
            </div>

            <div class="tab-content" id="products">
                <section class="gestion-products-container">
                    <h2>Produits en ligne :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="gestion-product-id-thead">Identifiant produit</th>
                            <th id="gestion-product-product-thead">Produit</th>
                            <th id="gestion-product-category-thead">Catégorie</th>
                            <th id="gestion-product-stock-thead">Stock</th>
                            <th id="gestion-product-action-thead">Action</th>
                        </tr>

                        <tr>
                            <td>7845920</td>
                            <td>Iphone 12</td>
                            <td>Smartphone</td>
                            <td><input type="number" value="2" min="0"></td>
                            <td><div><a href="#">Supprimer</a>  <a href="#">Modifier</a></div></td>
                        </tr>

                        <tr>
                            <td>89259</td>
                            <td>Iphone 14</td>
                            <td>Smartphone</td>
                            <td><input type="number" value="2" min="0"></td>
                            <td><div><a href="#">Supprimer</a>  <a href="#">Modifier</a></div></td>
                        </tr>
                    </table>

                    <a href="#" class="red-button">Ajouter un produit</a>
                </section>
            </div>

            <div class="tab-content" id="black-list">
                <section class="black-list-container">
                    <h2>Gestion de la liste noire :</h2>
                    <table>
                        <tr class="tr-head">
                            <th id="black-list-id-thead">Identifiant</th>
                            <th id="black-list-firstname-thead">Nom</th>
                            <th id="black-list-name-thead">Prénom</th>
                            <th id="black-list-action-thead">Action</th>
                        </tr>

                        <tr>
                            <td>36772</td>
                            <td>Dumartin</td>
                            <td>Hugo</td>
                            <td><a href="#">Débloquer</a></td>
                        </tr>

                        <tr>
                            <td>12098</td>
                            <td>Dempt</td>
                            <td>jules</td>
                            <td><a href="#">Débloquer</a></td>
                        </tr>
                    </table>
                    <a href="#" class="red-button">Ajouter à la liste</a>
                </section>
            </div>

        </div>
    </div>

</section>

<script src="${pageContext.request.contextPath}/js/product.js"></script>
