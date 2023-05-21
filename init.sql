CREATE DATABASE IF NOT EXISTS projet_shop;

USE projet_shop;

CREATE TABLE IF NOT EXISTS categories (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(30),
    description TEXT,
    image VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS products(
    id INT NOT NULL AUTO_INCREMENT,
    category_id INT NOT NULL,
    name VARCHAR(50),
    image VARCHAR(100),
    description TEXT,
    price FLOAT,
    quantity SMALLINT,
    attributes VARCHAR(1000),
    active BOOLEAN DEFAULT 1,
    PRIMARY KEY(id),
    FOREIGN KEY(category_id) REFERENCES categories(id)
);




CREATE TABLE IF NOT EXISTS users(
    email VARCHAR(64) NOT NULL, 
    phone VARCHAR(13),
    password VARCHAR(100) NOT NULL,
    name VARCHAR(20),
    firstname VARCHAR(20),
    address VARCHAR(255),
    is_admin BOOLEAN DEFAULT 0,
    is_blocked BOOLEAN DEFAULT 0,
    PRIMARY KEY(email)
);


CREATE TABLE IF NOT EXISTS discount_code(
    id VARCHAR(20) NOT NULL,
    discount INT NOT NULL,
    PRIMARY KEY(id)
);


CREATE TABLE IF NOT EXISTS orders(
    id INT NOT NULL AUTO_INCREMENT,
    customer_id VARCHAR(64) NOT NULL,
    date DATETIME,
    state TINYINT,
    shippingFees INT,
    discountCode VARCHAR(20),
    PRIMARY KEY(id),
    FOREIGN KEY(customer_id) REFERENCES users(email) ON DELETE CASCADE,
    FOREIGN KEY(discountCode) REFERENCES discount_code(id)
);


CREATE TABLE IF NOT EXISTS content_orders(
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    quantity SMALLINT,
    FOREIGN KEY(order_id) REFERENCES orders(id) ON DELETE CASCADE,
    FOREIGN KEY(product_id) REFERENCES products(id) ON DELETE CASCADE
);



/********************************************* Insert values *********************************************/

/***** Insert Categories ******/
INSERT INTO projet_shop.categories (id, name, description, image) VALUES (1, 'Smartphones', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit, suscipit?', 'phones.png');
INSERT INTO projet_shop.categories (id, name, description, image) VALUES (2, 'Tablettes', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit, suscipit?', 'ipad.png');
INSERT INTO projet_shop.categories (id, name, description, image) VALUES (3, 'Ordinateurs', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit, suscipit?', 'computer.png');
INSERT INTO projet_shop.categories (id, name, description, image) VALUES (4, 'Télévisions', 'Lorem ipsum dolor sit amet consectetur adipisicing elit. Sit, suscipit?', 'tv.png');


/***** Insert Products ******/
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (1, 1, 'Iphone 13 Mini', 'iphone-13-mini.png', 'L''iPhone 13 mini, d''Apple, est son smartphone le plus compact, il est doté d''un écran de 5,4 pouces. Il embarque le processeur Apple A15 Bionic, dispose de 128 Go, 256 Go ou 512 Go de stockage. Il est équipé d''un double module caméra principale (grand-angle et ultra-grand-angle), et est compatible avec la recharge sans-fil Qi et MagSafe..', 809, 2, '{}', 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (2, 1, 'Iphone 13 Pro Max', 'iphone-13-pro-max.png', 'L''iPhone 13 Pro Max, annoncé le 14 septembre 2021, est le modèle haut de gamme et grand format de la 15e itération du smartphone d''Apple. Il est équipé d''un écran OLED de 6,7 pouces 120 Hz, d''un SoC Apple A15 Bionic compatible 5G (NR & Sub-6) et d''un triple capteur photo de 12+12+12 mégapixels (grand-angle, téléobjectif et ultra grand-angle) avec OIS.', 1259, 15, '{}', 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (3, 1, 'Iphone 13 Pro', 'iphone-13-pro.png', 'L''iPhone 13 Pro, annoncé le 14 septembre 2021, est le modèle haut de gamme de la 15e itération du smartphone d''Apple. Il est équipé d''un écran OLED de 6,1 pouces 120 Hz, d''un SoC Apple A15 Bionic compatible 5G (NR & Sub-6) et d''un triple capteur photo de 12+12+12 mégapixels (grand-angle, téléobjectif et ultra grand-angle) avec OIS.', 1159, 34, '{}', 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (4, 1, 'Samsung Galaxy S21', 'Samsung_galaxy_S21.png', 'Le Samsung Galaxy S21 est un smartphone haut de gamme doté d''un écran de 6,2 pouces au taux de rafraîchissement de 120 Hz. Cet appareil se destine aux personnes qui cherchent un design élégant même si l''on peut déplorer que son dos soit recouvert de plastique plutôt que de verre. Il embarque le processeur Exynos 2100 et arbore un triple module caméra identique à celui de son prédécesseur. ', 629, 12, '{}', 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (5, 1, 'Iphone 10', 'iphone-10.png', 'L’Apple iPhone X affiche, pour la première fois de son histoire, un écran OLED sans bords de 5,8 pouces. Il embarque également le nouveau processeur A11 Bionic ainsi qu''un double module caméra principal amélioré de 12 mégapixels. Le capteur d''empreintes (Touch ID) fait place à de la reconnaissance faciale (Face ID).&', 619.99, 4, '{}', 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (6, 2, 'Galaxy Tab S7', 'Samsung_tab_S7.png', 'Super tablette Android', 719, 15, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (7, 4, 'LG C1OLED 4K 55"', 'LG_OLED55C1.png', 'Téléviseur connecté LG OLED 4K 55"', 1299, 4, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (8, 2, 'Galaxy Tab S7 FE', 'Samsung_tab_S7_fe.png', 'tablette fan edition samsung', 599, 15, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (9, 4, 'Philips Ambilight Oled 4K 48"', 'philips_ambilight_48.png', 'Téléviseur connecté Philips Ambilight Oled 4K 48"', 999, 2, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (11, 4, 'Philips Ambilight Oled 4K 55"', 'philips_ambilight_50.png', 'Téléviseur connecté Philips Ambilight Oled 4K 55"', 1299, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (12, 4, 'Philips Ambilight Oled 4K 65"', 'philips_ambilight_65.png', 'Téléviseur connecté Philips Ambilight Oled 4K 65"', 1999, 2, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (13, 4, 'Samsung QLED 8K 65"', 'samsung_neo_qled_8k.png', 'Le meilleurs des téléviseurs', 2790, 3, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (14, 2, 'Apple Ipad Pro', 'Apple_iPad_pro.png', 'Apple ipad pro puce M1', 1199, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (15, 2, 'Apple Ipad Air', 'Apple_iPad_air.png', 'Apple ipad air puce M1', 699, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (16, 2, 'Apple Ipad Mini', 'Apple_iPad_mini.png', 'Apple ipad mini puce A15', 559, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (17, 1, 'Samsung galaxy S22', 'Samsung_galaxy_S22.png', 'Tout dernier Smartphone de chez Samsung, le galaxy S22', 859, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (18, 3, 'Asus Tuf Gaming 15" RTX', 'Asus_tuf_15.png', 'Ordinateur gamming ultra puissant', 1199, 20, null, 1);
INSERT INTO projet_shop.products (id, category_id, name, image, description, price, quantity, attributes, active) VALUES (19, 3, 'Honor Magicbook pro', 'Honor_magicbook.png', 'Le Honor MagicBook Pro est un ordinateur portable officialisé en septembre 2020 en France. Il est équipé d''un CPU AMD Ryzen 5 4600H hexacoeur épaulé par 16 Go de RAM et 512 Go de stockage SSD. Il tourne sous Windows 10 et est éligible aux futures mises à jour de Windows.', 899, 20, null, 1);

/***** Insert users ******/
INSERT INTO projet_shop.users (email, phone, password, name, firstname, address, is_admin, is_blocked) VALUES ('hugo.dumartin@hotmail.fr', '0783116590', '$2a$10$GjYbzjqGpmnb4UZJbxf4T.l1.DxS1dfth.Y/cmVKtEVn0iFENPoc.', 'Dumartin', 'Hugo', '8 rue du guignier\\Soulaines sur Aubance\\49610\\Angleterre', 1, 0);
INSERT INTO projet_shop.users (email, phone, password, name, firstname, address, is_admin, is_blocked) VALUES ('jules.dempt@outlook.fr', null, '$2a$10$GjYbzjqGpmnb4UZJbxf4T.l1.DxS1dfth.Y/cmVKtEVn0iFENPoc.', 'Dempt', 'Jules', null, 0, 0);

/***** Insert orders ******/
INSERT INTO projet_shop.orders (id, customer_id, date, state, shippingFees, discountCode) VALUES (1, 'hugo.dumartin@hotmail.fr', '2022-03-13 13:38:26', 1, 0, null);
INSERT INTO projet_shop.orders (id, customer_id, date, state, shippingFees, discountCode) VALUES (2, 'jules.dempt@outlook.fr', '2022-03-14 10:06:06', 2, 0, null);
INSERT INTO projet_shop.orders (id, customer_id, date, state, shippingFees, discountCode) VALUES (3, 'jules.dempt@outlook.fr', '2022-03-16 11:39:12', 3, 0, null);
INSERT INTO projet_shop.orders (id, customer_id, date, state, shippingFees, discountCode) VALUES (4, 'jules.dempt@outlook.fr', '2022-03-16 16:54:47', 1, 0, null);
INSERT INTO projet_shop.orders (id, customer_id, date, state, shippingFees, discountCode) VALUES (5, 'hugo.dumartin@hotmail.fr', '2022-03-16 18:29:01', 2, 0, null);

/***** Insert content_orders ******/
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (1, 3, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (2, 3, 6);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (2, 17, 3);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (3, 7, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (4, 19, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (4, 16, 2);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (5, 1, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (5, 17, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (5, 8, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (5, 14, 1);
INSERT INTO projet_shop.content_orders (order_id, product_id, quantity) VALUES (5, 13, 1);