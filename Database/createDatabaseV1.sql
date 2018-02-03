-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Memorabilia
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema Memorabilia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Memorabilia` DEFAULT CHARACTER SET utf8 ;
USE `Memorabilia` ;

-- -----------------------------------------------------
-- Table `Memorabilia`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`User` (
  `idUser` INT NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  `filepath` VARCHAR(200) NULL,
  `alive` TINYINT NOT NULL,
  `joinTime` DATE NOT NULL,
  `whatsup` LONGTEXT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Memorabilia`.`Notebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`Notebook` (
  `idNotebook` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `createDate` DATE NOT NULL,
  `expireDate` DATE NOT NULL,
  `isPublic` TINYINT NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idNotebook`),
  INDEX `fk_Notebook_User_idx` (`User_idUser` ASC),
  CONSTRAINT `fk_Notebook_User`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Memorabilia`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Memorabilia`.`Diary`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`Diary` (
  `Diarycol` VARCHAR(45) NULL,
  `filepath` VARCHAR(200) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `createTime` TIMESTAMP NOT NULL,
  `Notebook_idNotebook` INT NOT NULL,
  PRIMARY KEY (`filepath`),
  INDEX `fk_Diary_Notebook1_idx` (`Notebook_idNotebook` ASC),
  CONSTRAINT `fk_Diary_Notebook1`
    FOREIGN KEY (`Notebook_idNotebook`)
    REFERENCES `Memorabilia`.`Notebook` (`idNotebook`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Memorabilia`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`Comment` (
  `idComment` INT NOT NULL,
  `content` TEXT NOT NULL,
  `User_idUser` INT NOT NULL,
  `Diary_filepath` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idComment`),
  INDEX `fk_Comment_User1_idx` (`User_idUser` ASC),
  INDEX `fk_Comment_Diary1_idx` (`Diary_filepath` ASC),
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Memorabilia`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Diary1`
    FOREIGN KEY (`Diary_filepath`)
    REFERENCES `Memorabilia`.`Diary` (`filepath`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
