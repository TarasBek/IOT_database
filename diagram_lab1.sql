-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Bek
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Bek
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Bek` DEFAULT CHARACTER SET utf8 ;
USE `Bek` ;

-- -----------------------------------------------------
-- Table `Bek`.`price_dependence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bek`.`price_dependence` (
  `id_price_dependence` INT NOT NULL AUTO_INCREMENT,
  `fast_delivering_price` FLOAT NOT NULL,
  `normal_delivering_price` FLOAT NOT NULL,
  PRIMARY KEY (`id_price_dependence`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bek`.`characteristics`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bek`.`characteristics` (
  `id_characteristics` INT NOT NULL AUTO_INCREMENT,
  `nutritional_value` INT NOT NULL,
  `weight` FLOAT NOT NULL,
  `country_of_manufacture` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_characteristics`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bek`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bek`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `description` VARCHAR(1000) NULL,
  `characteristics_id_characteristics` INT NOT NULL,
  `exp_date` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`id_product`),
  INDEX `fk_product_characteristics1_idx` (`characteristics_id_characteristics` ASC) VISIBLE,
  UNIQUE INDEX `price_UNIQUE` (`price` ASC) VISIBLE,
  CONSTRAINT `fk_product_characteristics1`
    FOREIGN KEY (`characteristics_id_characteristics`)
    REFERENCES `Bek`.`characteristics` (`id_characteristics`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Bek`.`store`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Bek`.`store` (
  `id_stores` INT NOT NULL AUTO_INCREMENT,
  `address` VARCHAR(45) NOT NULL,
  `price` FLOAT NOT NULL,
  `object_arrea` INT NOT NULL,
  `phone_number` INT NOT NULL,
  `price_dependence_id_price_dependence` INT NOT NULL,
  `product_id_product` INT NOT NULL,
  PRIMARY KEY (`id_stores`),
  UNIQUE INDEX `phone_number_UNIQUE` (`phone_number` ASC) VISIBLE,
  INDEX `fk_store_price_dependence1_idx` (`price_dependence_id_price_dependence` ASC) VISIBLE,
  INDEX `fk_store_product1_idx` (`product_id_product` ASC) VISIBLE,
  CONSTRAINT `fk_store_price_dependence1`
    FOREIGN KEY (`price_dependence_id_price_dependence`)
    REFERENCES `Bek`.`price_dependence` (`id_price_dependence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_store_product1`
    FOREIGN KEY (`product_id_product`)
    REFERENCES `Bek`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
