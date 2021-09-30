-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema bekk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bekk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bekk` DEFAULT CHARACTER SET utf8 ;
USE `bekk` ;

-- -----------------------------------------------------
-- Table `bekk`.`characteristics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`characteristics` (
  `idcharacteristics` INT NOT NULL AUTO_INCREMENT,
  `weight` INT NOT NULL,
  `nutritions` INT NOT NULL,
  PRIMARY KEY (`idcharacteristics`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bekk`.`product`
-- -----------------------------------------------------
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


-- -----------------------------------------------------
-- Table `bekk`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`order` (
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


-- -----------------------------------------------------
-- Table `bekk`.`user_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`user_address` (
  `idstore_address` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house_number` INT NOT NULL,
  `flat_number` INT NOT NULL,
  PRIMARY KEY (`idstore_address`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bekk`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`user` (
  `iduser` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `second_name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `user_address_idstore_address` INT NOT NULL,
  `order_idorder` INT NOT NULL,
  `usercol` VARCHAR(45) NULL,
  `user_address_idstore_address1` INT NOT NULL,
  PRIMARY KEY (`iduser`, `user_address_idstore_address`, `order_idorder`, `user_address_idstore_address1`),
  INDEX `fk_user_order1_idx` (`order_idorder` ASC) VISIBLE,
  INDEX `fk_user_user_address1_idx` (`user_address_idstore_address1` ASC) VISIBLE,
  CONSTRAINT `fk_user_order1`
    FOREIGN KEY (`order_idorder`)
    REFERENCES `bekk`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_address1`
    FOREIGN KEY (`user_address_idstore_address1`)
    REFERENCES `bekk`.`user_address` (`idstore_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bekk`.`store_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`store_address` (
  `idstore_address` INT NOT NULL AUTO_INCREMENT,
  `street` VARCHAR(45) NOT NULL,
  `city` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idstore_address`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bekk`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bekk`.`store` (
  `idstore` INT NOT NULL AUTO_INCREMENT,
  `user_iduser` INT NOT NULL,
  `user_user_address_idstore_address` INT NULL,
  `store_address_idstore_address` INT NOT NULL,
  PRIMARY KEY (`idstore`, `user_iduser`, `store_address_idstore_address`, `user_user_address_idstore_address`),
  INDEX `fk_store_user1_idx` (`user_iduser` ASC, `user_user_address_idstore_address` ASC) VISIBLE,
  INDEX `fk_store_store_address1_idx` (`store_address_idstore_address` ASC) VISIBLE,
  CONSTRAINT `fk_store_user1`
    FOREIGN KEY (`user_iduser` , `user_user_address_idstore_address`)
    REFERENCES `bekk`.`user` (`iduser` , `user_address_idstore_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_store_address1`
    FOREIGN KEY (`store_address_idstore_address`)
    REFERENCES `bekk`.`store_address` (`idstore_address`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
