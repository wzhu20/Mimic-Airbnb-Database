
CREATE SCHEMA IF NOT EXISTS `airbnb` DEFAULT CHARACTER SET utf8 ;
USE `airbnb` ;

CREATE TABLE IF NOT EXISTS `airbnb`.`HomeType` (
  `idHomeType` INT NOT NULL AUTO_INCREMENT,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idHomeType`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Listing` (
  `ListingID` INT NOT NULL AUTO_INCREMENT,
  `Latitutude` FLOAT(10) NOT NULL,
  `Longitude` FLOAT(10) NOT NULL,
  `HomeType_idHomeType` INT NOT NULL,
  PRIMARY KEY (`ListingID`),
  CONSTRAINT `fk_Listing_HomeType1`
    FOREIGN KEY (`HomeType_idHomeType`)
    REFERENCES `airbnb`.`HomeType` (`idHomeType`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Calendar` (
  `BeginDate` DATE NOT NULL,
  `EndDate` DATE NOT NULL,
  PRIMARY KEY (`BeginDate`, `EndDate`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Listing_has_Calendar` (
  `Listing_ListingID` INT NOT NULL,
  `Calendar_BeginDate` DATE NOT NULL,
  `Calendar_EndDate` DATE NOT NULL,
  `RentalPrice` DOUBLE NOT NULL,
  PRIMARY KEY (`Listing_ListingID`, `Calendar_BeginDate`, `Calendar_EndDate`),
  CONSTRAINT `fk_Listing_has_Calendar1_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Listing_has_Calendar1_Calendar1`
    FOREIGN KEY (`Calendar_BeginDate` , `Calendar_EndDate`)
    REFERENCES `airbnb`.`Calendar` (`BeginDate` , `EndDate`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Amenities` (
  `idAmenities` INT NOT NULL AUTO_INCREMENT,
  `Item` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAmenities`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Listing_has_Amenities` (
  `Listing_ListingID` INT NOT NULL,
  `Amenities_idAmenities` INT NOT NULL,
  PRIMARY KEY (`Listing_ListingID`, `Amenities_idAmenities`),
  CONSTRAINT `fk_Listing_has_Amenities_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Listing_has_Amenities_Amenities1`
    FOREIGN KEY (`Amenities_idAmenities`)
    REFERENCES `airbnb`.`Amenities` (`idAmenities`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Host_Profile` (
  `idHost_Profile` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idHost_Profile`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Renter_Profile` (
  `idRenter_Profile` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`idRenter_Profile`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Users_has_Occupation` (
  `Occupation_idOccupation` INT NOT NULL AUTO_INCREMENT,
  `Occupation_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Occupation_idOccupation`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Users` (
  `SIN` INT NOT NULL,
  `Date_of_Birth` VARCHAR(45) NOT NULL,
  `Full_Name` VARCHAR(45) NOT NULL,
  `Host_Profile_idHost_Profile` INT,
  `Renter_Profile_idRenter_Profile` INT,
  `Occupation` INT NOT NULL,
  PRIMARY KEY (`SIN`),
  CONSTRAINT `fk_Users_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_Renter_Profile1`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_Occupation`
    FOREIGN KEY (`Occupation`)
    REFERENCES `airbnb`.`Users_has_Occupation` (`Occupation_idOccupation`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Users_Host_Listing` (
  `Users_SIN` INT NOT NULL,
  `Listing_ListingID` INT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_Users_has_Listing_Users1`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_has_Listing_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Users_rent_Listing` (
  `Users_SIN` INT NOT NULL,
  `Listing_ListingID` INT NOT NULL,
  `Credit_Card` INT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_Users_has_Listing_Users2`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_has_Listing_Listing2`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Address` (
  `Postal_Code` VARCHAR(6) NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Postal_Code`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Address_has_Listing` (
  `Address_Postal_Code` VARCHAR(6) NOT NULL,
  `Listing_ListingID` INT NOT NULL,
  PRIMARY KEY (`Address_Postal_Code`, `Listing_ListingID`),
  CONSTRAINT `fk_Address_has_Listing_Address1`
    FOREIGN KEY (`Address_Postal_Code`)
    REFERENCES `airbnb`.`Address` (`Postal_Code`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Address_has_Listing_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Experience` (
  `idExperience` INT NOT NULL,
  `Experience` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idExperience`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Users_has_Address` (
  `Users_SIN` INT NOT NULL,
  `Address_Postal_Code` VARCHAR(6) NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Address_Postal_Code`),
  CONSTRAINT `fk_Users_has_Address_Users1`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Users_has_Address_Address1`
    FOREIGN KEY (`Address_Postal_Code`)
    REFERENCES `airbnb`.`Address` (`Postal_Code`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Rating` (
  `Users_Host_Listing_Users_SIN` INT NOT NULL,
  `Users_Host_Listing_Listing_ListingID` INT NOT NULL,
  `Users_rent_Listing_Users_SIN` INT NOT NULL,
  `Users_rent_Listing_Listing_ListingID` INT NOT NULL,
  PRIMARY KEY (`Users_Host_Listing_Users_SIN`, `Users_Host_Listing_Listing_ListingID`, `Users_rent_Listing_Users_SIN`, `Users_rent_Listing_Listing_ListingID`),
  CONSTRAINT `fk_Users_Host_Listing_has_Users_rent_Listing_Users_Host_Listing1`
    FOREIGN KEY (`Users_Host_Listing_Users_SIN` , `Users_Host_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`Users_Host_Listing` (`Users_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_Host_Listing_has_Users_rent_Listing_Users_rent_Listing1`
    FOREIGN KEY (`Users_rent_Listing_Users_SIN` , `Users_rent_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`Users_rent_Listing` (`Users_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Comment` (
  `idComment` INT NOT NULL,
  `Users_Host_Listing_Users_SIN` INT NOT NULL,
  `Users_Host_Listing_Listing_ListingID` INT NOT NULL,
  `Users_rent_Listing_Users_SIN` INT NOT NULL,
  `Users_rent_Listing_Listing_ListingID` INT NOT NULL,
  PRIMARY KEY (`idComment`),
  CONSTRAINT `fk_Comment_Users_Host_Listing1`
    FOREIGN KEY (`Users_Host_Listing_Users_SIN` , `Users_Host_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`Users_Host_Listing` (`Users_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_Users_rent_Listing1`
    FOREIGN KEY (`Users_rent_Listing_Users_SIN` , `Users_rent_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`Users_rent_Listing` (`Users_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Comment_On_Renter_Profile` (
  `Comment_idComment` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Renter_Profile_Users_SIN` INT NOT NULL,
  PRIMARY KEY (`Comment_idComment`, `Renter_Profile_idRenter_Profile`, `Renter_Profile_Users_SIN`),
  CONSTRAINT `fk_Comment_has_Renter_Profile_Comment1`
    FOREIGN KEY (`Comment_idComment`)
    REFERENCES `airbnb`.`Comment` (`idComment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_has_Renter_Profile_Renter_Profile1`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Comment_On_Host_Profile` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Host_Profile_Users_SIN` INT NOT NULL,
  `Comment_idComment` INT NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `Host_Profile_Users_SIN`, `Comment_idComment`),
  CONSTRAINT `fk_Host_Profile_has_Comment_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Host_Profile_has_Comment_Comment1`
    FOREIGN KEY (`Comment_idComment`)
    REFERENCES `airbnb`.`Comment` (`idComment`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Profile Comments` (
  `Users_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Comment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Renter_Profile_idRenter_Profile`, `Host_Profile_idHost_Profile`),
  CONSTRAINT `fk_Users_has_Renter_Profile_Users1`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Renter_Profile_Renter_Profile1`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comments On_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Listing Comments` (
  `Users_SIN` INT NOT NULL,
  `Listing_ListingID` INT NOT NULL,
  `Comment` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_Users_has_Listing_Users3`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Listing_Listing3`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Host Comments` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Users_SIN` INT NOT NULL,
  `Comment` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `Users_SIN`),
  CONSTRAINT `fk_Host_Profile_has_Users_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Host_Profile_has_Users_Users1`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Renter Comment` (
  `Users_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Comment` MEDIUMTEXT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Renter_Profile_idRenter_Profile`),
  CONSTRAINT `fk_Users_has_Renter_Profile_Users2`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Renter_Profile_Renter_Profile2`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Listing Rating` (
  `Users_SIN` INT NOT NULL,
  `Listing_ListingID` INT NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_Users_has_Listing_Users4`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Listing_Listing4`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `airbnb`.`Renter Rating` (
  `Users_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`Users_SIN`, `Renter_Profile_idRenter_Profile`),
  CONSTRAINT `fk_Users_has_Renter_Profile_Users3`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Users_has_Renter_Profile_Renter_Profile3`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `airbnb`.`Host Rating` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Users_SIN` INT NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `Users_SIN`),
  CONSTRAINT `fk_Host_Profile_has_Users_Host_Profile2`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Host_Profile_has_Users_Users2`
    FOREIGN KEY (`Users_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
