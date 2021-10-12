CREATE DATABASE IF NOT EXISTS bekk;
USE bekk;


DROP TABLE IF EXISTS store;
DROP TABLE IF EXISTS store_address;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS user_address;
DROP TABLE IF EXISTS order1;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS characteristics;


CREATE TABLE IF NOT EXISTS `bekk`.`characteristics` (
  `idcharacteristics` INT NOT NULL AUTO_INCREMENT,
  `weight` INT NOT NULL,
  `nutritions` INT NOT NULL,
  PRIMARY KEY (`idcharacteristics`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`product` (
  `idproduct` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `characteristics_idcharacteristics` INT NOT NULL,
  PRIMARY KEY (`idproduct`, `characteristics_idcharacteristics`),
  INDEX `fk_product_characteristics1_idx` (`characteristics_idcharacteristics` ASC) VISIBLE,
  CONSTRAINT `fk_product_characteristics1`
    FOREIGN KEY (`characteristics_idcharacteristics`)
    REFERENCES `bekk`.`characteristics` (`idcharacteristics`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`order1` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `price` FLOAT NULL,
  `product_idproduct` INT NOT NULL,
  PRIMARY KEY (`idorder`, `product_idproduct`),
  INDEX `fk_order_product1_idx` (`product_idproduct` ASC) VISIBLE,
  CONSTRAINT `fk_order_product1`
    FOREIGN KEY (`product_idproduct`)
    REFERENCES `bekk`.`product` (`idproduct`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`user_address` (
  `iduser_address` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  `flat_number` INT NOT NULL,
  PRIMARY KEY (`iduser_address`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `user_address_iduser_address` INT NOT NULL,
  `order_idorder` INT NOT NULL,
  `usercol` VARCHAR(45) NULL,
  PRIMARY KEY (`iduser`, `user_address_iduser_address`, `order_idorder`),
  INDEX `fk_user_order1_idx` (`order_idorder` ASC) VISIBLE,
  INDEX `fk_user_user_address_idx` (`user_address_iduser_address` ASC) VISIBLE,
  CONSTRAINT `fk_user_order1`
    FOREIGN KEY (`order_idorder`)
    REFERENCES `bekk`.`order1` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_address`
    FOREIGN KEY (`user_address_iduser_address`)
    REFERENCES `bekk`.`user_address` (`iduser_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`store_address` (
  `idstore_address` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idstore_address`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `bekk`.`store` (
  `idstore` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `user_user_address_iduser_address` INT NOT NULL,
  `store_address_idstore_address` INT NOT NULL,
  PRIMARY KEY (`idstore`, `user_iduser`,  `user_user_address_iduser_address`, `store_address_idstore_address`),
  INDEX `fk_store_user1_idx` (`user_iduser` ASC, `user_user_address_iduser_address` ASC) VISIBLE,
  INDEX `fk_store_store_address1_idx` (`store_address_idstore_address` ASC) VISIBLE,
  CONSTRAINT `fk_store_use1`
    FOREIGN KEY (`user_iduser` , `user_user_address_iduser_address`)
    REFERENCES `bekk`.`user` (`iduser` , `user_address_iduser_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_store_address1`
    FOREIGN KEY (`store_address_idstore_address`)
    REFERENCES `bekk`.`store_address` (`idstore_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
	

INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (1,12,223);
INSERT INTO characteristics  (`idcharacteristics`,`weight`,`nutritions`) VALUES (2,100,443);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (3,233,432);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (4,321,123);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (5,432,321);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (6,123,543);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (7,123,432);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (8,123,234);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (9,133,443);
INSERT INTO characteristics (`idcharacteristics`,`weight`,`nutritions`) VALUES (10,452,645);

INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (1,'banana',23,1);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (2,'bread',14,2);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (3,'water',31,3);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (4,'milk',32,4);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (5,'mango',77,5);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (6,'potato',12,6);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (7,'chips',51,7);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (8,'cream',41,8);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (9,'biscuit',22,9);
INSERT INTO product (`idproduct`,`name`,`price`,`characteristics_idcharacteristics`) VALUES (10,'cake',333,10);


INSERT INTO order1 (`idorder`,`price`,`product_idproduct`) VALUES (1,433,1);
INSERT INTO store_address (`idstore_address`,`street`,`city`) VALUES (1,'Stryiska','Lviv');
INSERT INTO user_address (`iduser_address`,`city`,`street`,`house_number`,`flat_number`) VALUES (1,'Lviv','Soborna',4,23);
INSERT INTO user (`iduser`,`first_name`,`second_name`,`email`,`user_address_iduser_address`,`order_idorder`) VALUES (1,'Olena','Olena','olena20@gmail.com',1,1);
INSERT INTO store (`idstore`,`user_iduser`,`user_user_address_iduser_address`,`store_address_idstore_address`) VALUES (11,1,1,1);


CREATE INDEX  price_index ON product(price);
CREATE INDEX  weight_index ON characteristics(weight);