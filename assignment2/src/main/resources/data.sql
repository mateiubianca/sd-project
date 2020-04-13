
INSERT INTO user (id, username, password, wallet, role) VALUES
(1, "a", "a", 0, "ADMIN"),
(2, "b", "b", 50, "USER");

INSERT INTO flower (id, name, price) VALUES
(1, "trandafir", 5),
(2, "cala", 3);

INSERT INTO bouquet (id, name) VALUES
(1, "buchet de trandafiri"),
(2, "buchet de cale si trandafiri");

INSERT INTO bouquet_flower (bouquet_id, flower_id, quantity) VALUES
(1, 1, 19),
(2, 2, 5),
(2, 1, 2);

INSERT INTO items (type, item) VALUES
("FLOWER", 1),
("FLOWER", 2),
("BOUQUET", 1),
("BOUQUET", 2);

INSERT INTO orders (user_id, item_id) VALUE
(2, 1);