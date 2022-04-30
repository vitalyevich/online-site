-- Table: users
CREATE TABLE users (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    user_code VARCHAR(6) NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    date_of_birth DATE NULL,
    email VARCHAR(20) NULL,
    invite_code VARCHAR(6) NULL,
    UNIQUE (email),
    UNIQUE(user_code),
    PRIMARY KEY(id)
);

-- Table: roles
CREATE TABLE roles (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    role_name VARCHAR(20) NOT NULL,
    UNIQUE(role_name),
    PRIMARY KEY (id)
);

-- Table for mapping user and roles: user_roles
CREATE TABLE user_roles (
    user_id  INT NOT NULL,
    role_id  INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, role_id)
);

-- Table: access
CREATE TABLE access (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    user_id INT NOT NULL,
    phone VARCHAR(19) NOT NULL,
    password VARCHAR(255) NOT NULL,
    active BOOLEAN NOT NULL,
    UNIQUE (phone),
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table: categories
CREATE TABLE categories (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    category_name VARCHAR(20) NOT NULL,
    UNIQUE (category_name),
    PRIMARY KEY (id)
);

-- Table: photos
CREATE TABLE photos (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    path VARCHAR(255) NOT NULL,
    name VARCHAR(255) NOT NULL,
    format VARCHAR(20) NOT NULL,
    PRIMARY KEY (id)
);

-- Table: products
CREATE TABLE products (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    category_id INT NOT NULL,
    product_name VARCHAR(40) NOT NULL,
    photo_id INT NOT NULL,
    amount INT NOT NULL,
    weight INT NOT NULL,
    FOREIGN KEY (photo_id) REFERENCES photos (id) ON DELETE CASCADE,
    FOREIGN KEY (category_id) REFERENCES categories (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table: prices
CREATE TABLE prices(
   product_id INT NOT NULL,
   price FLOAT NOT NULL,
   discount INT NOT NULL,
   FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
   PRIMARY KEY (product_id)
);

-- Table: ingredients
CREATE TABLE ingredients (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    ingredient_name VARCHAR(30) NOT NULL,
    UNIQUE (ingredient_name),
    PRIMARY KEY (id)
);

-- Table for mapping product and ingredients: product_ingredients
CREATE TABLE product_ingredients (
    product_id INT NOT NULL,
    ingredient_id INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id)  ON DELETE CASCADE,
    FOREIGN KEY (ingredient_id) REFERENCES ingredients (id) ON DELETE CASCADE,
    PRIMARY KEY (product_id, ingredient_id)
);

-- Table: cities
CREATE TABLE cities (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    city_name VARCHAR(20),
    PRIMARY KEY (id)
);

-- Table: addresses
CREATE TABLE addresses (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    city_id INT NOT NULL,
    street VARCHAR(20) NOT NULL,
    entrance INT NULL,
    floor INT NULL,
    home INT NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table for mapping user and addresses: user_addresses
CREATE TABLE user_addresses (
    client_id INT NOT NULL,
    address_id INT NOT NULL,
    FOREIGN KEY (address_id) REFERENCES addresses (id) ON DELETE CASCADE,
    FOREIGN KEY (client_id) REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (client_id, address_id)
);

-- Table: baskets
CREATE TABLE baskets (
    user_id INT NOT NULL,
    product_id INT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (user_id, product_id)
);

-- Table: payments
CREATE TABLE payments (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    payment_name VARCHAR(20),
    PRIMARY KEY (id)
);

-- Table: orders
CREATE TABLE orders (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    type VARCHAR(30) NOT NULL,
    order_date TIMESTAMP NOT NULL,
    end_date TIMESTAMP NOT NULL,
    access_id INT NOT NULL,
    address_id INT NOT NULL,
    payment_id INT NOT NULL,
    comment VARCHAR(255) NULL,
    price FLOAT NOT NULL,
    status VARCHAR(255) DEFAULT 'В очереди' NOT NULL,
    FOREIGN KEY (access_id) REFERENCES access (id),
    FOREIGN KEY (address_id) REFERENCES addresses (id) ON DELETE CASCADE,
    FOREIGN KEY (payment_id) REFERENCES payments (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table for mapping order and products: order_products
CREATE TABLE order_products (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    order_id INT NOT NULL,
    product_id INT NOT NULL,
    amount INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

--Table: actions
CREATE TABLE actions (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    name VARCHAR(40) NOT NULL,
    product_id INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    status BOOLEAN NOT NULL,
    FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table: deliveries
CREATE TABLE deliveries(
    id INT GENERATED BY DEFAULT AS IDENTITY,
    order_id INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table: points
CREATE TABLE points (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    min_cost INT NOT NULL,
    value INT NOT NULL,
    PRIMARY KEY (id)
);

-- Table: history_points
CREATE TABLE history_points (
    id INT GENERATED BY DEFAULT AS IDENTITY,
    add_date TIMESTAMP NOT NULL,
    user_id INT NOT NULL,
    size INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    PRIMARY KEY (id)
);

-- Table for mapping user and points: user_points
CREATE TABLE user_points (
    user_id INT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    balance INT DEFAULT 0 NOT NULL,
    PRIMARY KEY (user_id)
);

-- Insert data

INSERT INTO cities VALUES ('1', 'Столбцы');
INSERT INTO cities VALUES ('2', 'Минск');

INSERT INTO addresses VALUES (1, 1, 'Центральная', 4, 4, 24);
INSERT INTO user_addresses VALUES (1,1);

INSERT INTO payments VALUES (1,'Наличными');
INSERT INTO payments VALUES (2,'Картой');

INSERT INTO orders VALUES (1,'Самовывоз' ,'2022-05-01 12:00','2022-05-01 17:00',1,1,1,NULL,24);
INSERT INTO order_products VALUES (1, 1, 1, 3);
INSERT INTO order_products VALUES (2, 1, 6, 1);

INSERT INTO actions VALUES (1, 'Суши в подарок', 2, '2022-05-01', '2022-05-07','Подарок при заказе от 40 рублей!',TRUE);
INSERT INTO actions VALUES (2, 'Суши в подарок', 2, '2022-05-01', '2022-05-07','Подарок при заказе от 40 рублей!',TRUE);

INSERT INTO user_points VALUES (1, 5);

INSERT INTO users VALUES (1, 'INVITE','Максим','24-11-2001','maksim_2411@mail.ru');
INSERT INTO users VALUES (2, 'INVITE','Павел','20-11-2001','pavel_2411@mail.ru');

INSERT INTO roles VALUES (1, 'ROLE_USER');
INSERT INTO roles VALUES (2, 'ROLE_ADMIN');

INSERT INTO user_roles VALUES (1,2);
INSERT INTO user_roles VALUES (2,1);

INSERT INTO access VALUES (1, 1, '+375 (29) 575-64-10', 'qwerty', true);
INSERT INTO access VALUES (2, 2, '+375 (29) 545-64-10', 'qwerty', true);

INSERT INTO photos VALUES (1, '/images/rolls/', 'filadelfiya-klassika', '.png');
INSERT INTO photos VALUES (2, '/images/rolls/', 'syake-avokado-maki', '.png');
INSERT INTO photos VALUES (3, '/images/rolls/', 'bonito-maki', '.png');
INSERT INTO photos VALUES (4, '/images/rolls/', 'kaliforniya-syake-s-ikroj', '.png');
INSERT INTO photos VALUES (5, '/images/rolls/', 'kalifa-tempura', '.png');
INSERT INTO photos VALUES (6, '/images/rolls/', 'raduga', '.png');
INSERT INTO photos VALUES (7, '/images/rolls/', 'unagi-avokado-maki', '.png');
INSERT INTO photos VALUES (8, '/images/sushi/', 'sushi-syake', '.png');
INSERT INTO photos VALUES (9, '/images/sushi/', 'sushi-ebi', '.png');
INSERT INTO photos VALUES (10, '/images/sushi/', 'sushi-unagi', '.png');
INSERT INTO photos VALUES (11, '/images/sushi/', 'sushi-maguro', '.png');
INSERT INTO photos VALUES (12, '/images/woks/', 'ris-s-moreproduktami', '.png');
INSERT INTO photos VALUES (13, '/images/woks/', 'ris-s-kuricej', '.png');
INSERT INTO photos VALUES (14, '/images/woks/', 'ris-s-ovoshhami', '.png');
INSERT INTO photos VALUES (15, '/images/soups/', 'miso-sup', '.png');
INSERT INTO photos VALUES (16, '/images/soups/', 'sup-tom-yami', '.png');
INSERT INTO photos VALUES (17, '/images/sauces/', 'imbir', '.png');
INSERT INTO photos VALUES (18, '/images/sauces/', 'orehovyj', '.png');
INSERT INTO photos VALUES (19, '/images/sauces/', 'soevyj', '.png');
INSERT INTO photos VALUES (20, '/images/sauces/', 'tiriyaki', '.png');
INSERT INTO photos VALUES (21, '/images/sauces/', 'vassabi', '.png');

INSERT INTO photos VALUES (22, '/images/drinks/', 'dobryj-yabloko', '.png');
INSERT INTO photos VALUES (23, '/images/drinks/', 'fanta', '.png');
INSERT INTO photos VALUES (24, '/images/drinks/', 'kola', '.png');
INSERT INTO photos VALUES (25, '/images/drinks/', 'sprajt', '.png');

INSERT INTO photos VALUES (26, '/images/sets/', 'sikoku', '.png');

/*граммы чтобы само считало*/

INSERT INTO products VALUES (1,1, 'Филадельфия классика', 1, 8, 270);
INSERT INTO products VALUES (2,1, 'Сяке авокадо маки', 2, 8, 190);
INSERT INTO products VALUES (3,1, 'Бонито маки', 3, 8, 190);
INSERT INTO products VALUES (4,1, 'Калифорния сяке с икрой', 4, 8, 210);
INSERT INTO products VALUES (5,1, 'Калифорния темпура', 5, 8, 235);
INSERT INTO products VALUES (6,1, 'Радуга', 6, 8, 240);
INSERT INTO products VALUES (7,1, 'Унаги авокадо маки', 7, 8, 190);
INSERT INTO products VALUES (8,2, 'Суши сяке', 8, 1, 30);
INSERT INTO products VALUES (9,2, 'Суши эби', 9, 1, 30);
INSERT INTO products VALUES (10,2, 'Суши унаги', 10, 1, 30);
INSERT INTO products VALUES (11,2, 'Суши магуро', 11, 1, 30);
INSERT INTO products VALUES (12,3, 'Рис с морепродуктами', 12, 1, 320);
INSERT INTO products VALUES (13,3, 'Рис с курицей', 13, 1, 320);
INSERT INTO products VALUES (14,3, 'Рис с овощами', 14, 1, 320);
INSERT INTO products VALUES (15,4, 'Суп Мисо', 15, 1, 300);
INSERT INTO products VALUES (16,4, 'Суп Том Ям (острый)', 16, 1, 300);
INSERT INTO products VALUES (17,5, 'Имбирь', 17, 1, 30);
INSERT INTO products VALUES (18,5, 'Соус Ореховый', 18, 1, 40);
INSERT INTO products VALUES (19,5, 'Соус Соевый', 19, 1, 35);
INSERT INTO products VALUES (20,5, 'Соус Терияки', 20, 1, 40);
INSERT INTO products VALUES (21,5, 'Васаби', 21, 1, 10);
INSERT INTO products VALUES (22,6, 'Сок Добрый яблочный 0,2 л', 22, 1, 200);
INSERT INTO products VALUES (23,6, 'Фанта апельсин 0,5 л', 23, 1, 200);
INSERT INTO products VALUES (24,6, 'Кока-Кола 0,5 л', 24, 1, 330);
INSERT INTO products VALUES (25,6, 'Спрайт 0,5 л', 25, 1, 200);
INSERT INTO products VALUES (26,7, 'Сет По Любви', 26, 48, 1300);

INSERT INTO prices VALUES (1, 19.90, 0);
INSERT INTO prices VALUES (2, 12.90, 0);
INSERT INTO prices VALUES (3, 12.90, 0);
INSERT INTO prices VALUES (4, 18.90, 0);
INSERT INTO prices VALUES (5, 8.90, 0);
INSERT INTO prices VALUES (6, 18.90, 0);
INSERT INTO prices VALUES (7, 14.90, 0);
INSERT INTO prices VALUES (8, 2.90, 0);
INSERT INTO prices VALUES (9, 3.90, 0);
INSERT INTO prices VALUES (10, 3.90, 0);
INSERT INTO prices VALUES (11, 3.90, 0);
INSERT INTO prices VALUES (12, 18.50, 0);
INSERT INTO prices VALUES (13, 10.90, 0);
INSERT INTO prices VALUES (14, 10.50, 0);
INSERT INTO prices VALUES (15, 7.50, 0);
INSERT INTO prices VALUES (16, 14.90, 0);
INSERT INTO prices VALUES (17,1.20, 0);
INSERT INTO prices VALUES (18,2.90, 0);
INSERT INTO prices VALUES (19,1.20, 0);
INSERT INTO prices VALUES (20,2.50, 0);
INSERT INTO prices VALUES (21,1.20, 0);
INSERT INTO prices VALUES (22,2, 0);
INSERT INTO prices VALUES (23,3, 0);
INSERT INTO prices VALUES (24,3, 0);
INSERT INTO prices VALUES (25,3, 0);
INSERT INTO prices VALUES (26,54.90, 0);

/* можно добавить граммы в ингредиенты*/
INSERT INTO ingredients VALUES  (1, 'лосось');
INSERT INTO ingredients VALUES  (2, 'сливочный сыр');
INSERT INTO ingredients VALUES  (3, 'нори');
INSERT INTO ingredients VALUES  (4, 'рис');
INSERT INTO ingredients VALUES  (5, 'авокадо');
INSERT INTO ingredients VALUES  (6, 'свежий огурец');
INSERT INTO ingredients VALUES  (7, 'стружка тунца');
INSERT INTO ingredients VALUES  (8, 'икра тобико');
INSERT INTO ingredients VALUES  (9, 'снежный краб');
INSERT INTO ingredients VALUES  (10, 'соус спайси');
INSERT INTO ingredients VALUES  (11, 'кляр');
INSERT INTO ingredients VALUES  (12, 'сухари панко');
INSERT INTO ingredients VALUES  (12, 'угорь');
INSERT INTO ingredients VALUES  (13, 'тунец');
INSERT INTO ingredients VALUES  (14, 'соус унаги');
INSERT INTO ingredients VALUES  (15, 'кунжут');
INSERT INTO ingredients VALUES  (16, 'креветка тигровая');
INSERT INTO ingredients VALUES  (17, 'креветка');
INSERT INTO ingredients VALUES  (18, 'кальмар');
INSERT INTO ingredients VALUES  (19, 'овощной микс');
INSERT INTO ingredients VALUES  (20, 'имбирно-чесночный соус');
INSERT INTO ingredients VALUES  (21, 'соус терияки');
INSERT INTO ingredients VALUES  (22, 'филе грудки цыпленка');
INSERT INTO ingredients VALUES  (23, 'грибы шиитаке');
INSERT INTO ingredients VALUES  (24, 'водоросли вакамэ');
INSERT INTO ingredients VALUES  (25, 'тофу');
INSERT INTO ingredients VALUES  (26, 'лук зеленый');
INSERT INTO ingredients VALUES  (27, 'бульон мисо');
INSERT INTO ingredients VALUES  (28, 'помидоры');
INSERT INTO ingredients VALUES  (29, 'бульон том-ям');
INSERT INTO ingredients VALUES  (30, 'кокосовое молоко');
INSERT INTO ingredients VALUES  (31, 'имбирь');
INSERT INTO ingredients VALUES  (32, 'васаби');
INSERT INTO ingredients VALUES  (33, 'филадельфия');
INSERT INTO ingredients VALUES  (34, 'лава с крабом');
INSERT INTO ingredients VALUES  (35, 'калифорния каппа маки');
INSERT INTO ingredients VALUES  (36, 'нежный с курицей');
INSERT INTO ingredients VALUES  (37, 'оушен темпура');


INSERT INTO product_ingredients VALUES (26, 33);
INSERT INTO product_ingredients VALUES (26, 34);
INSERT INTO product_ingredients VALUES (26, 35);
INSERT INTO product_ingredients VALUES (26, 36);
INSERT INTO product_ingredients VALUES (26, 37);

INSERT INTO product_ingredients VALUES (17, 31);
INSERT INTO product_ingredients VALUES (21,32);

INSERT INTO product_ingredients VALUES (16,22);
INSERT INTO product_ingredients VALUES (16,17);
INSERT INTO product_ingredients VALUES (16,18);
INSERT INTO product_ingredients VALUES (16,23);
INSERT INTO product_ingredients VALUES (16,28);
INSERT INTO product_ingredients VALUES (16,29);

INSERT INTO product_ingredients VALUES (15,24);
INSERT INTO product_ingredients VALUES (15,23);
INSERT INTO product_ingredients VALUES (15,25);
INSERT INTO product_ingredients VALUES (15,26);
INSERT INTO product_ingredients VALUES (15,27);

INSERT INTO product_ingredients VALUES (14,23);
INSERT INTO product_ingredients VALUES (14,19);
INSERT INTO product_ingredients VALUES (14,4);
INSERT INTO product_ingredients VALUES (14,15);
INSERT INTO product_ingredients VALUES (14,20);
INSERT INTO product_ingredients VALUES (14,21);

INSERT INTO product_ingredients VALUES (13,22);
INSERT INTO product_ingredients VALUES (13,19);
INSERT INTO product_ingredients VALUES (13,4);
INSERT INTO product_ingredients VALUES (13,15);
INSERT INTO product_ingredients VALUES (13,20);
INSERT INTO product_ingredients VALUES (13,21);

INSERT INTO product_ingredients VALUES (12,17);
INSERT INTO product_ingredients VALUES (12,18);
INSERT INTO product_ingredients VALUES (12,19);
INSERT INTO product_ingredients VALUES (12,4);
INSERT INTO product_ingredients VALUES (12,15);
INSERT INTO product_ingredients VALUES (12,20);
INSERT INTO product_ingredients VALUES (12,21);

INSERT INTO product_ingredients VALUES (11,13);
INSERT INTO product_ingredients VALUES (11,4);

INSERT INTO product_ingredients VALUES (10,12);
INSERT INTO product_ingredients VALUES (10,4);
INSERT INTO product_ingredients VALUES (10,3);

INSERT INTO product_ingredients VALUES (9,16);
INSERT INTO product_ingredients VALUES (9,4);

INSERT INTO product_ingredients VALUES (8,1);
INSERT INTO product_ingredients VALUES (8,4);

INSERT INTO product_ingredients VALUES (7,12);
INSERT INTO product_ingredients VALUES (7,4);
INSERT INTO product_ingredients VALUES (7,3);
INSERT INTO product_ingredients VALUES (7,2);
INSERT INTO product_ingredients VALUES (7,5);
INSERT INTO product_ingredients VALUES (7,14);
INSERT INTO product_ingredients VALUES (7,15);


INSERT INTO product_ingredients VALUES (6,1);
INSERT INTO product_ingredients VALUES (6,12);
INSERT INTO product_ingredients VALUES (6,13);
INSERT INTO product_ingredients VALUES (6,4);
INSERT INTO product_ingredients VALUES (6,2);
INSERT INTO product_ingredients VALUES (6,6);
INSERT INTO product_ingredients VALUES (6,14);
INSERT INTO product_ingredients VALUES (6,15);
INSERT INTO product_ingredients VALUES (6,3);

INSERT INTO product_ingredients VALUES (5,9);
INSERT INTO product_ingredients VALUES (5,4);
INSERT INTO product_ingredients VALUES (5,6);
INSERT INTO product_ingredients VALUES (5,8);
INSERT INTO product_ingredients VALUES (5,10);
INSERT INTO product_ingredients VALUES (5,11);
INSERT INTO product_ingredients VALUES (5,12);
INSERT INTO product_ingredients VALUES (5,3);

INSERT INTO product_ingredients VALUES (1,1);
INSERT INTO product_ingredients VALUES (1,2);
INSERT INTO product_ingredients VALUES (1,3);
INSERT INTO product_ingredients VALUES (1,4);

INSERT INTO product_ingredients VALUES (2,1);
INSERT INTO product_ingredients VALUES (2,4);
INSERT INTO product_ingredients VALUES (2,3);
INSERT INTO product_ingredients VALUES (2,2);
INSERT INTO product_ingredients VALUES (2,5);

INSERT INTO product_ingredients VALUES (3,1);
INSERT INTO product_ingredients VALUES (3,4);
INSERT INTO product_ingredients VALUES (3,3);
INSERT INTO product_ingredients VALUES (3,2);
INSERT INTO product_ingredients VALUES (3,6);
INSERT INTO product_ingredients VALUES (3,7);

INSERT INTO product_ingredients VALUES (4,1);
INSERT INTO product_ingredients VALUES (4,4);
INSERT INTO product_ingredients VALUES (4,2);
INSERT INTO product_ingredients VALUES (4,8);
INSERT INTO product_ingredients VALUES (4,6);
INSERT INTO product_ingredients VALUES (4,3);

INSERT INTO categories VALUES (1, 'Роллы');
INSERT INTO categories VALUES (2, 'Суши');
INSERT INTO categories VALUES (3, 'Рис Wok');
INSERT INTO categories VALUES (4, 'Супы');
INSERT INTO categories VALUES (5, 'Соусы');
INSERT INTO categories VALUES (6, 'Напитки');
INSERT INTO categories VALUES (7, 'Сеты');

