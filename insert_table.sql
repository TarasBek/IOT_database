
INSERT INTO `` (`iduser`,`first_name`,`second_name`,`email`,`user_address_idstore_address`,`order_idorder`) VALUES (1,'Olena','Olena','olena20@gmail.com',1,1);


--characteristics

INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (1,12,223);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (2,100,443);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (3,233,432);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (4,321,123);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (5,432,321);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (6,123,543);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (7,123,432);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (8,123,234);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (9,133,443);
INSERT INTO `` (`idcharacteristics`,`weight`,`nutritions`) VALUES (10,452,645);

--oreder

INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (1,433,1);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (29,432,2);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (30,546,3);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (31,232,4);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (32,22,5);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (33,111,6);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (34,765,7);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (35,453,8);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (36,857,9);
INSERT INTO `` (`idorder`,`price`,`product_idproduct`) VALUES (37,123,10);

--product

INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (1,'banana',23,1);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (2,'bread',14,2);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (3,'water',31,3);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (4,'milk',32,4);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (5,'mango',77,5);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (6,'potato',12,6);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (7,'chips',51,7);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (8,'cream',41,8);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (9,'biscuit',22,9);
INSERT INTO `` (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (10,'cake',333,10);

--store

INSERT INTO `` (`idstore`,`user_iduser`,`user_user_address_idstore_address`,`store_address_idstore_address`) VALUES (11,1,1,1);

--store_address

INSERT INTO `` (`idstore_address`,`street`,`city`) VALUES (1,'Stryiska','Lviv');

--user


INSERT INTO `` (`iduser`,`first_name`,`second_name`,`email`,`user_address_idstore_address`,`order_idorder`) VALUES (1,'Olena','Olena','olena20@gmail.com',1,1);

--user_adderss

INSERT INTO `` (`idstore_address`,`city`,`street`,`house_number`,`flat_number`) VALUES (1,'Lviv','Soborna',4,23);

