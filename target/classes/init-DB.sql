-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema shop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `shop` DEFAULT CHARACTER SET utf8 ;
USE `shop` ;

-- -----------------------------------------------------
-- Table `shop`.`destination_address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`destination_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(45) NOT NULL,
  `city_vilage` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `building` VARCHAR(45) NOT NULL,
  `flat` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`products`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `weight` VARCHAR(45) NOT NULL,
  `nutritions` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`order` (
  `idorder` INT NOT NULL AUTO_INCREMENT,
  `destination_address_id` INT NOT NULL,
  `products_id` INT NOT NULL,
  PRIMARY KEY (`idorder`),
  INDEX `fk_order_destination_address_idx` (`destination_address_id` ASC) VISIBLE,
  INDEX `fk_order_products1_idx` (`products_id` ASC) VISIBLE,
  CONSTRAINT `fk_order_destination_address`
    FOREIGN KEY (`destination_address_id`)
    REFERENCES `shop`.`destination_address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_products1`
    FOREIGN KEY (`products_id`)
    REFERENCES `shop`.`products` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`transaction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`transaction` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `data_of_transaction` VARCHAR(45) NOT NULL,
  `sum` VARCHAR(45) NOT NULL,
  `discount_in_percent` VARCHAR(45) NOT NULL,
  `status_of_transaction` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`general_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`general_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `general_infocol` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `phone` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`social_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`social_info` (
  `idsocial_info` INT NOT NULL AUTO_INCREMENT,
  `facebook` VARCHAR(45) NOT NULL,
  `instagram` VARCHAR(45) NOT NULL,
  `twitter` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idsocial_info`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`user_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`user_info` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `general_info_id` INT NOT NULL,
  `social_info_idsocial_info` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_info_general_info1_idx` (`general_info_id` ASC) VISIBLE,
  INDEX `fk_user_info_social_info1_idx` (`social_info_idsocial_info` ASC) VISIBLE,
  CONSTRAINT `fk_user_info_general_info1`
    FOREIGN KEY (`general_info_id`)
    REFERENCES `shop`.`general_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_info_social_info1`
    FOREIGN KEY (`social_info_idsocial_info`)
    REFERENCES `shop`.`social_info` (`idsocial_info`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `shop`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `shop`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `order_idorder` INT NOT NULL,
  `transaction_id` INT NOT NULL,
  `user_info_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_user_order1_idx` (`order_idorder` ASC) VISIBLE,
  INDEX `fk_user_transaction1_idx` (`transaction_id` ASC) VISIBLE,
  INDEX `fk_user_user_info1_idx` (`user_info_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_order1`
    FOREIGN KEY (`order_idorder`)
    REFERENCES `shop`.`order` (`idorder`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_transaction1`
    FOREIGN KEY (`transaction_id`)
    REFERENCES `shop`.`transaction` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_user_user_info1`
    FOREIGN KEY (`user_info_id`)
    REFERENCES `shop`.`user_info` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
