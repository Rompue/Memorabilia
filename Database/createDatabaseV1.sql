-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema Memorabilia
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `Memorabilia` ;

-- -----------------------------------------------------
-- Schema Memorabilia
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `Memorabilia` DEFAULT CHARACTER SET utf8 ;
USE `Memorabilia` ;

-- -----------------------------------------------------
-- Table `Memorabilia`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`User` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `filepath` VARCHAR(200) NULL,
  `alive` TINYINT NOT NULL,
  `joinTime` DATE NOT NULL,
  `intro` LONGTEXT NULL,
  `salt` VARCHAR(24) NULL,
  `hash` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`idUser`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `idUser_UNIQUE` (`idUser` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `Memorabilia`.`Notebook`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `Memorabilia`.`Notebook` (
  `idNotebook` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `createDate` DATE NOT NULL,
  `expireDate` DATE NOT NULL,
  `isPublic` TINYINT NOT NULL,
  `User_idUser` INT NOT NULL,
  PRIMARY KEY (`idNotebook`),
  INDEX `fk_Notebook_User_idx` (`User_idUser` ASC),
  UNIQUE INDEX `idNotebook_UNIQUE` (`idNotebook` ASC),
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
  `idDiary` INT NOT NULL AUTO_INCREMENT,
  `filepath` VARCHAR(200) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `createTime` TIMESTAMP NOT NULL,
  `Notebook_idNotebook` INT NOT NULL,
  PRIMARY KEY (`idDiary`),
  INDEX `fk_Diary_Notebook1_idx` (`Notebook_idNotebook` ASC),
  UNIQUE INDEX `idDiary_UNIQUE` (`idDiary` ASC),
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
  `idComment` INT NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `User_idUser` INT NOT NULL,
  `Diary_idDiary` INT NOT NULL,
  PRIMARY KEY (`idComment`),
  INDEX `fk_Comment_User1_idx` (`User_idUser` ASC),
  INDEX `fk_Comment_Diary1_idx` (`Diary_idDiary` ASC),
  UNIQUE INDEX `idComment_UNIQUE` (`idComment` ASC),
  CONSTRAINT `fk_Comment_User1`
    FOREIGN KEY (`User_idUser`)
    REFERENCES `Memorabilia`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Diary1`
    FOREIGN KEY (`Diary_idDiary`)
    REFERENCES `Memorabilia`.`Diary` (`idDiary`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
