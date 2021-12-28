-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema 6lab
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema 6lab
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `6lab` DEFAULT CHARACTER SET utf8 ;
USE `6lab` ;

-- -----------------------------------------------------
-- Table `6lab`.`subject`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`subject`;
CREATE TABLE `6lab`.`subject` (
                                          `id` INT NOT NULL AUTO_INCREMENT,
                                          `name` VARCHAR(45) NOT NULL,
                                          `cluster_program_id` INT NOT NULL,
                                          PRIMARY KEY (`id`),
                                          INDEX `fk_subject_cluster_program1_idx` (`cluster_program_id` ASC) VISIBLE,
                                          CONSTRAINT `fk_subject_cluster_program1`
                                              FOREIGN KEY (`cluster_program_id`)
                                                  REFERENCES `6lab`.`cluster_program` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `6lab`.`student_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`student_group`;
CREATE TABLE `6lab`.`student_group` (
                                                `id` INT NOT NULL AUTO_INCREMENT,
                                                `name` VARCHAR(45) NOT NULL,
                                                `entry_year` INT NOT NULL,
                                                PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `6lab`.`student`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`student`;
CREATE TABLE `6lab`.`student` (
                                          `id` INT NOT NULL AUTO_INCREMENT,
                                          `surname` VARCHAR(45) NOT NULL,
                                          `student_group_id` INT NOT NULL,
                                          `subject_id` INT NOT NULL,
                                          PRIMARY KEY (`id`),
                                          INDEX `fk_student_student_group1_idx` (`student_group_id` ASC) VISIBLE,
                                          INDEX `fk_student_subject1_idx` (`subject_id` ASC) VISIBLE,
                                          CONSTRAINT `fk_student_student_group1`
                                              FOREIGN KEY (`student_group_id`)
                                                  REFERENCES `6lab`.`student_group` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION,
                                          CONSTRAINT `fk_student_subject1`
                                              FOREIGN KEY (`subject_id`)
                                                  REFERENCES `6lab`.`subject` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `6lab`.`response`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`response`;
CREATE TABLE `6lab`.`response` (
                                           `id` INT NOT NULL AUTO_INCREMENT,
                                           `data` INT NOT NULL,
                                           `text` VARCHAR(200) NOT NULL,
                                           `rating` INT NOT NULL,
                                           `student_id` INT NOT NULL,
                                           PRIMARY KEY (`id`),
                                           INDEX `fk_response_student1_idx` (`student_id` ASC) VISIBLE,
                                           CONSTRAINT `fk_response_student1`
                                               FOREIGN KEY (`student_id`)
                                                   REFERENCES `6lab`.`student` (`id`)
                                                   ON DELETE NO ACTION
                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `6lab`.`cluster_program`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`cluster_program`;
CREATE TABLE `6lab`.`cluster_program` (
                                                  `id` INT NOT NULL AUTO_INCREMENT,
                                                  `name` VARCHAR(45) NOT NULL,
                                                  `time_of_event` VARCHAR(45) NOT NULL,
                                                  PRIMARY KEY (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `6lab`.`lecturer`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`lecturer`;
CREATE TABLE `6lab`.`lecturer` (
                                           `id` INT NOT NULL AUTO_INCREMENT,
                                           `surname` VARCHAR(45) NOT NULL,
                                           `subject_id` INT NOT NULL,
                                           PRIMARY KEY (`id`),
                                           INDEX `fk_lecturer_subject_idx` (`subject_id` ASC) VISIBLE,
                                           CONSTRAINT `fk_lecturer_subject`
                                               FOREIGN KEY (`subject_id`)
                                                   REFERENCES `6lab`.`subject` (`id`))
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `6lab`.`speaker`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `6lab`.`speaker`;
CREATE TABLE `6lab`.`speaker` (
                                          `id` INT NOT NULL AUTO_INCREMENT,
                                          `surname` VARCHAR(45) NOT NULL,
                                          `firm` VARCHAR(45) NOT NULL,
                                          `subject_id` INT NOT NULL,
                                          PRIMARY KEY (`id`),
                                          INDEX `fk_speakers_subject1_idx` (`subject_id` ASC) VISIBLE,
                                          CONSTRAINT `fk_speakers_subject1`
                                              FOREIGN KEY (`subject_id`)
                                                  REFERENCES `6lab`.`subject` (`id`)
                                                  ON DELETE NO ACTION
                                                  ON UPDATE NO ACTION)
    ENGINE = InnoDB;

USE `6lab` ;

-- subject
INSERT INTO `6lab`.`subject`(`name`, `cluster_program_id`) VALUES ('Math', 1);
INSERT INTO `6lab`.`subject`(`name`, `cluster_program_id`) VALUES ('Web', 1);
INSERT INTO `6lab`.`subject`(`name`, `cluster_program_id`) VALUES ('Data', 1);
INSERT INTO `6lab`.`subject`(`name`, `cluster_program_id`) VALUES ('Algo', 1);
INSERT INTO `6lab`.`subject`(`name`, `cluster_program_id`) VALUES ('Eng', 1);

-- speaker
INSERT INTO `6lab`.`speaker`(`surname`, `firm`, `subject_id`) VALUES ('Kyba', 'SoftServe', 1);
INSERT INTO `6lab`.`speaker`(`surname`, `firm`, `subject_id`) VALUES ('Veres', 'SoftServe', 4);
INSERT INTO `6lab`.`speaker`(`surname`, `firm`, `subject_id`) VALUES ('Mask', 'SpaceX', 3);
INSERT INTO `6lab`.`speaker`(`surname`, `firm`, `subject_id`) VALUES ('Einstein', 'World', 2);
INSERT INTO `6lab`.`speaker`(`surname`, `firm`, `subject_id`) VALUES ('Newton', 'World', 2);

-- lecturer
INSERT INTO `6lab`.`lecturer`(`surname`, `subject_id`) VALUES ('Rubak', '1');
INSERT INTO `6lab`.`lecturer`(`surname`, `subject_id`) VALUES ('Ivanov', '1');
INSERT INTO `6lab`.`lecturer`(`surname`, `subject_id`) VALUES ('Bek', '3');
INSERT INTO `6lab`.`lecturer`(`surname`, `subject_id`) VALUES ('Marchyk', '4');
INSERT INTO `6lab`.`lecturer`(`surname`, `subject_id`) VALUES ('Ivanyk', '5');

-- cluster_program
INSERT INTO `6lab`.`cluster_program`(`name`, `time_of_event`) VALUES ('IT', '100');
INSERT INTO `6lab`.`cluster_program`(`name`, `time_of_event`) VALUES ('Languages', '10');
INSERT INTO `6lab`.`cluster_program`(`name`, `time_of_event`) VALUES ('Frontent', '10');
INSERT INTO `6lab`.`cluster_program`(`name`, `time_of_event`) VALUES ('Backend', '10');
INSERT INTO `6lab`.`cluster_program`(`name`, `time_of_event`) VALUES ('BigData', '10');

-- student
INSERT INTO `6lab`.`student`(`surname`, `student_group_id`, `subject_id`) VALUES ('6lab', 1, 1);
INSERT INTO `6lab`.`student`(`surname`, `student_group_id`, `subject_id`) VALUES ('Vavrunchyk', 2, 5);
INSERT INTO `6lab`.`student`(`surname`, `student_group_id`, `subject_id`) VALUES ('Androsiyk', 3, 3);
INSERT INTO `6lab`.`student`(`surname`, `student_group_id`, `subject_id`) VALUES ('Dmytryshyn', 3, 2);
INSERT INTO `6lab`.`student`(`surname`, `student_group_id`, `subject_id`) VALUES ('Deinecka', 4, 1);

-- student_group
INSERT INTO `6lab`.`student_group`(`name`, `entry_year`) VALUES ('IR-21', 2020);
INSERT INTO `6lab`.`student_group`(`name`, `entry_year`) VALUES ('IR-11', 2021);
INSERT INTO `6lab`.`student_group`(`name`, `entry_year`) VALUES ('IR-22', 2020);
INSERT INTO `6lab`.`student_group`(`name`, `entry_year`) VALUES ('IR-23', 2020);
INSERT INTO `6lab`.`student_group`(`name`, `entry_year`) VALUES ('IR-12', 2021);

-- response
INSERT INTO `6lab`.`response`(`data`, `text`, `rating`, `student_id`) VALUES (12, 'really good performance', 5, 1);
INSERT INTO `6lab`.`response`(`data`, `text`, `rating`, `student_id`) VALUES (17, 'really bad performance', 2, 5);
INSERT INTO `6lab`.`response`(`data`, `text`, `rating`, `student_id`) VALUES (30, 'really ideal performance', 1, 1);
INSERT INTO `6lab`.`response`(`data`, `text`, `rating`, `student_id`) VALUES (15, 'really perfect performance', 4, 1);
INSERT INTO `6lab`.`response`(`data`, `text`, `rating`, `student_id`) VALUES (19, 'really terrible performance', 3, 2);

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;