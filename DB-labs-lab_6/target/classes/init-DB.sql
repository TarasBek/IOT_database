-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema Oliinyk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Oliinyk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Oliinyk` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema oliinyk
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema oliinyk
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `oliinyk` DEFAULT CHARACTER SET utf8 ;
USE `Oliinyk` ;

-- -----------------------------------------------------
-- Table `Oliinyk`.`reporter`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`reporter`;
CREATE TABLE `Oliinyk`.`reporter` (
  `phone_number` VARCHAR(12) NOT NULL,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(40) NULL,
  PRIMARY KEY (`phone_number`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`call_address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`call_address`;
CREATE TABLE `Oliinyk`.`call_address` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `region` VARCHAR(100) NOT NULL,
  `city_or_village` VARCHAR(100) NOT NULL,
  `street` VARCHAR(45) NULL,
  `building` VARCHAR(45) NULL,
  `flat` VARCHAR(45) NULL,
  `place` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  INDEX `city_or_village_idx` (`city_or_village` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`call`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`call`;
CREATE TABLE `Oliinyk`.`call` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `reporter_phone_number` VARCHAR(12) NOT NULL,
  `short_description` VARCHAR(255) NOT NULL,
  `detailed_description` VARCHAR(3000) NULL,
  `call_address_id` INT NOT NULL,
  `call_time` DATETIME(6) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_call_reporter_idx` (`reporter_phone_number` ASC) VISIBLE,
  INDEX `fk_call_call_address1_idx` (`call_address_id` ASC) VISIBLE,
  CONSTRAINT `fk_call_reporter`
    FOREIGN KEY (`reporter_phone_number`)
    REFERENCES `Oliinyk`.`reporter` (`phone_number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_call_call_address1`
    FOREIGN KEY (`call_address_id`)
    REFERENCES `Oliinyk`.`call_address` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`rescue_vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`rescue_vehicle`;
CREATE TABLE `Oliinyk`.`rescue_vehicle` (
  `number` VARCHAR(8) NOT NULL,
  `characteristics` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`number`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`call_has_rescue_vehicle`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`call_has_rescue_vehicle`;
CREATE TABLE `Oliinyk`.`call_has_rescue_vehicle` (
  `call_id` INT NOT NULL,
  `rescue_vehicle_number` VARCHAR(8) NOT NULL,
  `departure_time` DATETIME(6) NOT NULL,
  `return_time` DATETIME(6),
  PRIMARY KEY (`call_id`, `rescue_vehicle_number`),
  INDEX `fk_call_has_rescue_vehicles_rescue_vehicles1_idx` (`rescue_vehicle_number` ASC) VISIBLE,
  INDEX `fk_call_has_rescue_vehicles_call1_idx` (`call_id` ASC) VISIBLE,
  CONSTRAINT `fk_call_has_rescue_vehicles_call1`
    FOREIGN KEY (`call_id`)
    REFERENCES `Oliinyk`.`call` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_call_has_rescue_vehicles_rescue_vehicles1`
    FOREIGN KEY (`rescue_vehicle_number`)
    REFERENCES `Oliinyk`.`rescue_vehicle` (`number`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`rescuer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`rescuer`;
CREATE TABLE `Oliinyk`.`rescuer` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `surname` VARCHAR(50) NOT NULL,
  `name` VARCHAR(40) NOT NULL,
  `position` VARCHAR(45) NOT NULL,
  `is_present` TINYINT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `surname_idx` (`surname` ASC) INVISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`hospital`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`hospital`;
CREATE TABLE `Oliinyk`.`hospital` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`injury`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`injury`;
CREATE TABLE `Oliinyk`.`injury` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `description` VARCHAR(1000) NOT NULL,
  `diagnosis` VARCHAR(255) NOT NULL,
  `hospital_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_injury_hospital1_idx` (`hospital_id` ASC) VISIBLE,
  CONSTRAINT `fk_injury_hospital1`
    FOREIGN KEY (`hospital_id`)
    REFERENCES `Oliinyk`.`hospital` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Oliinyk`.`call_has_rescuer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `Oliinyk`.`call_has_rescuer`;
CREATE TABLE `Oliinyk`.`call_has_rescuer` (
  `call_id` INT NOT NULL,
  `rescuer_id` INT NOT NULL,
  `injury_id` INT NULL,
  PRIMARY KEY (`call_id`, `rescuer_id`),
  INDEX `fk_call_has_rescuer_rescuer1_idx` (`rescuer_id` ASC) VISIBLE,
  INDEX `fk_call_has_rescuer_call1_idx` (`call_id` ASC) VISIBLE,
  INDEX `fk_call_has_rescuer_injury1_idx` (`injury_id` ASC) VISIBLE,
  UNIQUE INDEX `injury_id_UNIQUE` (`injury_id` ASC) VISIBLE,
  CONSTRAINT `fk_call_has_rescuer_call1`
    FOREIGN KEY (`call_id`)
    REFERENCES `Oliinyk`.`call` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_call_has_rescuer_rescuer1`
    FOREIGN KEY (`rescuer_id`)
    REFERENCES `Oliinyk`.`rescuer` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_call_has_rescuer_injury1`
    FOREIGN KEY (`injury_id`)
    REFERENCES `Oliinyk`.`injury` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

USE `oliinyk` ;

-- POPULATION SCRIPT
-- call_address
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `building`, `flat`) VALUES ('Lviv', 'Lviv', 'Skoryny', '55', '12');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `building`, `flat`) VALUES ('Lviv', 'Lviv', 'Skoryny', '29', '108');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `place`) VALUES ('Lviv', 'Lviv', 'Near theatre of opera and ballet');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `place`) VALUES ('Lviv', 'Svirzh', 'Near church');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `place`) VALUES ('Lviv', 'Yasninska', 'Lake near the park');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `place`) VALUES ('Odessa', 'Zatoka', 'Soniachna', 'Poligraphist camp');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `building`, `place`) VALUES ('Lviv', 'Lviv', 'Promyslova', '13', 'Roof');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `building`, `flat`) VALUES ('Lviv', 'Lviv', 'Stryiska', '118', '23');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `place`) VALUES ('Lviv', 'Navariya', 'On the lake');
INSERT INTO `oliinyk`.`call_address` (`region`, `city_or_village`, `street`, `building`, `flat`) VALUES ('Lviv', 'Lviv', 'Promyslova', '118', '23');

-- reporter
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380440609242', 'Nicholson', 'Jack');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380449209451', 'Marlon', 'Brando');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380635556157', 'De Nitro', 'Robert');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380395552787', 'Al', 'Pacino');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380395559610', 'Hoffman', 'Dustin');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380635554248', 'Hanks', 'Tom');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380505557912', 'Hopkins', 'Anthony');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380505556432', 'Newman', 'Paul');
INSERT INTO `oliinyk`.`reporter` (`phone_number`, `surname`, `name`) VALUES ('380417155524', 'Trasy', 'Spencer');

-- call
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `detailed_description`, `call_address_id`, `call_time`) VALUES ('380440609242', 'Boy broke his leg', 'Jumped down from the tree', '3', '2006-12-30 00:38:54.840');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `detailed_description`, `call_address_id`, `call_time`) VALUES ('380440609242', 'Boy broke his arm', 'During soccer', '5', '2006-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380449209451', 'Car stuck in the mud', '9', '2007-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380635556157', 'House on fire', '5', '2007-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380635554248', 'Car crash', '3', '2007-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380417155524', 'Robbery', '4', '2006-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380417155524', 'Flood', '2', '2008-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380505556432', 'Robbery', '6', '2008-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380505556432', 'Car crash', '7', '2008-12-31 00:38:54.840000');
INSERT INTO `oliinyk`.`call` (`reporter_phone_number`, `short_description`, `call_address_id`, `call_time`) VALUES ('380417155524', 'Flood', '1', '2008-12-31 00:38:54.840000');

-- rescuer
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Oliver', 'Laurence', 'RESCUER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Lemmon', 'Jack', 'RESCUER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Caine', 'Michael', 'RESCUER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Stewat', 'James', 'DOCTOR', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Williams', 'Robin', 'DOCTOR', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Duvall', 'Robert', 'RESCUER', '0');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Penn', 'Sean', 'RESCUER', '0');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Freeman', 'Morgan', 'DRIVER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Bridges', 'Jeff', 'DRIVER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Robert', 'Downey', 'DRIVER', '1');
INSERT INTO `oliinyk`.`rescuer` (`surname`, `name`, `position`, `is_present`) VALUES ('Poitier', 'Sidney', 'FIREFIGHTER', '1');

-- rescue_vehicle
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('JRH5633', 'NissanSentra S / SL / SR / SV');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('FJB759', 'MazdaMazda6 i');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('7PHT809', 'NissanAltima Base / S / SL');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('124253', 'BuickCentury Custom');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('JFA9130', 'NissanAltima Base / S / SL');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('3LSG492', 'PontiacFirebird');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('7LHB983', 'ScionxB Base / Release Series 8.0');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('8EHZ462', 'HYUNDAISANTA FE SE');
INSERT INTO `oliinyk`.`rescue_vehicle` (`number`, `characteristics`) VALUES ('CFX244', 'DodgeDart GT');

-- call_has_rescue_vehicle
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('1', 'JRH5633', '2006-12-30 00:39:54.840', '2006-12-30 02:38:54.840');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('1', 'FJB759', '2006-12-30 00:39:54.840', '2006-12-30 02:38:54.840');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('1', '7PHT809', '2006-12-30 00:39:54.840', '2006-12-30 02:18:03.840');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('3', 'JRH5633', '2007-12-31 00:40:54.840000', '2007-12-31 02:40:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('3', 'JFA9130', '2007-12-31 00:40:54.840000', '2007-12-31 02:40:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('4', '3LSG492', '2007-12-31 00:38:54.840000', '2007-12-31 04:38:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('5', '3LSG492', '2007-12-31 00:38:54.840000', '2007-12-31 04:38:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('5', '8EHZ462', '2007-12-31 00:38:54.840000', '2007-12-31 04:38:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('7', '3LSG492', '2008-12-31 00:38:54.840000', '2008-12-31 00:59:54.840000');
INSERT INTO `oliinyk`.`call_has_rescue_vehicle` (`call_id`, `rescue_vehicle_number`, `departure_time`, `return_time`) VALUES ('7', '8EHZ462', '2008-12-31 00:38:54.840000', '2008-12-31 00:59:54.840000');

-- hospital
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Children’s Hospitals');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('OKHMADYT Lviv Regional Children’s Clinical Hospital');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Lviv Regional Children’s Specialized Clinical Hospital');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Hospitals');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Municipal Clinical Hospital No. 10');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Municipal Communal Clinical Hospital No. 3');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Communal Clinical Hospital No. 4');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Communal Municipal Clinical Hospital No. 5');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Military Medical Service of the Office of Security Service of Ukraine in Lviv Region');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('State Border Guard Service Hospital');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Polyclinic of the Office of Ministry of Internal Affairs of Ukraine, Public Health Department in Lviv Region');
INSERT INTO `oliinyk`.`hospital` (`name`) VALUES ('Lviv Regional Medical Rehabilitation Hospital');

-- injury
INSERT INTO `oliinyk`.`injury` (`description`, `diagnosis`, `hospital_id`) VALUES ('Tried to stop drunk driver', 'Multiple fractures', '3');
INSERT INTO `oliinyk`.`injury` (`description`, `diagnosis`, `hospital_id`) VALUES ('Jumped from second floor', 'Stretched tendons', '11');
INSERT INTO `oliinyk`.`injury` (`description`, `diagnosis`, `hospital_id`) VALUES ('Was involved in a fight', 'Concussion', '1');

-- call_has_rescuer
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('1', '8');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('1', '9');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('1', '11');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`, `injury_id`) VALUES ('1', '1', '1');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('1', '2');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('1', '3');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('3', '8');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('3', '11');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('3', '10');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('4', '5');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('5', '9');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('5', '11');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('5', '2');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`, `injury_id`) VALUES ('5', '3', '3');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('5', '4');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('7', '8');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`) VALUES ('7', '9');
INSERT INTO `oliinyk`.`call_has_rescuer` (`call_id`, `rescuer_id`, `injury_id`) VALUES ('7', '5', '2');

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
