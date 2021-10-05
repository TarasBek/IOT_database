-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema labor_sql
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema labor_sql
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `labor_sql` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `labor_sql` ;

-- -----------------------------------------------------
-- Table `labor_sql`.`battles`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`battles` (
  `name` VARCHAR(20) NOT NULL,
  `date` DATETIME NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`classes` (
  `class` VARCHAR(50) NOT NULL,
  `type` VARCHAR(2) NOT NULL,
  `country` VARCHAR(20) NOT NULL,
  `numGuns` TINYINT NULL DEFAULT NULL,
  `bore` DOUBLE NULL DEFAULT NULL,
  `displacement` INT NULL DEFAULT NULL,
  PRIMARY KEY (`class`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`company`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`company` (
  `ID_comp` INT NOT NULL,
  `name` CHAR(10) NOT NULL,
  PRIMARY KEY (`ID_comp`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`income`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`income` (
  `code` INT NOT NULL,
  `point` TINYINT NOT NULL,
  `date` DATETIME NOT NULL,
  `inc` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`income_o`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`income_o` (
  `point` TINYINT NOT NULL,
  `date` DATETIME NOT NULL,
  `inc` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`point`, `date`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`product` (
  `maker` VARCHAR(10) NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`model`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`laptop`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`laptop` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `speed` SMALLINT NOT NULL,
  `ram` SMALLINT NOT NULL,
  `hd` DOUBLE NOT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT NULL,
  `screen` TINYINT NOT NULL,
  PRIMARY KEY (`code`),
  INDEX `FK_Laptop_product` (`model` ASC) VISIBLE,
  CONSTRAINT `FK_Laptop_product`
    FOREIGN KEY (`model`)
    REFERENCES `labor_sql`.`product` (`model`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`outcome`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`outcome` (
  `code` INT NOT NULL,
  `point` TINYINT NOT NULL,
  `date` DATETIME NOT NULL,
  `out` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`code`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`outcome_o`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`outcome_o` (
  `point` TINYINT NOT NULL,
  `date` DATETIME NOT NULL,
  `out` DECIMAL(8,2) NOT NULL,
  PRIMARY KEY (`point`, `date`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`outcomes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`outcomes` (
  `ship` VARCHAR(50) NOT NULL,
  `battle` VARCHAR(20) NOT NULL,
  `result` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ship`, `battle`),
  INDEX `FK_Outcomes_Battles` (`battle` ASC) VISIBLE,
  CONSTRAINT `FK_Outcomes_Battles`
    FOREIGN KEY (`battle`)
    REFERENCES `labor_sql`.`battles` (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`passenger`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`passenger` (
  `ID_psg` INT NOT NULL,
  `name` CHAR(20) NOT NULL,
  PRIMARY KEY (`ID_psg`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`trip` (
  `trip_no` INT NOT NULL,
  `ID_comp` INT NOT NULL,
  `plane` CHAR(10) NOT NULL,
  `town_from` CHAR(25) NOT NULL,
  `town_to` CHAR(25) NOT NULL,
  `time_out` DATETIME NOT NULL,
  `time_in` DATETIME NOT NULL,
  PRIMARY KEY (`trip_no`),
  INDEX `FK_Trip_Company` (`ID_comp` ASC) VISIBLE,
  CONSTRAINT `FK_Trip_Company`
    FOREIGN KEY (`ID_comp`)
    REFERENCES `labor_sql`.`company` (`ID_comp`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`pass_in_trip`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`pass_in_trip` (
  `trip_no` INT NOT NULL,
  `date` DATETIME NOT NULL,
  `ID_psg` INT NOT NULL,
  `place` CHAR(10) NOT NULL,
  PRIMARY KEY (`trip_no`, `date`, `ID_psg`),
  INDEX `FK_Pass_in_trip_Passenger` (`ID_psg` ASC) VISIBLE,
  CONSTRAINT `FK_Pass_in_trip_Passenger`
    FOREIGN KEY (`ID_psg`)
    REFERENCES `labor_sql`.`passenger` (`ID_psg`),
  CONSTRAINT `FK_Pass_in_trip_Trip`
    FOREIGN KEY (`trip_no`)
    REFERENCES `labor_sql`.`trip` (`trip_no`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`pc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`pc` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `speed` SMALLINT NOT NULL,
  `ram` SMALLINT NOT NULL,
  `hd` DOUBLE NOT NULL,
  `cd` VARCHAR(10) NOT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`code`),
  INDEX `FK_pc_product` (`model` ASC) VISIBLE,
  CONSTRAINT `FK_pc_product`
    FOREIGN KEY (`model`)
    REFERENCES `labor_sql`.`product` (`model`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`printer`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`printer` (
  `code` INT NOT NULL,
  `model` VARCHAR(50) NOT NULL,
  `color` CHAR(1) NOT NULL,
  `type` VARCHAR(10) NOT NULL,
  `price` DECIMAL(8,2) NULL DEFAULT NULL,
  PRIMARY KEY (`code`),
  INDEX `FK_printer_product` (`model` ASC) VISIBLE,
  CONSTRAINT `FK_printer_product`
    FOREIGN KEY (`model`)
    REFERENCES `labor_sql`.`product` (`model`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `labor_sql`.`ships`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `labor_sql`.`ships` (
  `name` VARCHAR(50) NOT NULL,
  `class` VARCHAR(50) NOT NULL,
  `launched` SMALLINT NULL DEFAULT NULL,
  PRIMARY KEY (`name`),
  INDEX `FK_Ships_Classes` (`class` ASC) VISIBLE,
  CONSTRAINT `FK_Ships_Classes`
    FOREIGN KEY (`class`)
    REFERENCES `labor_sql`.`classes` (`class`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
