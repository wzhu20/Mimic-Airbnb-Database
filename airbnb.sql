
-- -----------------------------------------------------
-- Schema airbnb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `airbnb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE `airbnb` ;

-- -----------------------------------------------------
-- Table `airbnb`.`HomeType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`HomeType` (
  `idHomeType` INT NOT NULL,
  `Type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idHomeType`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Listing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Listing` (
  `Latitutude` VARCHAR(45) NOT NULL,
  `Longitude` VARCHAR(45) NOT NULL,
  `ListingID` VARCHAR(45) NOT NULL,
  `HomeType_idHomeType` INT NOT NULL,
  PRIMARY KEY (`ListingID`),
  CONSTRAINT `fk_Listing_HomeType1`
    FOREIGN KEY (`HomeType_idHomeType`)
    REFERENCES `airbnb`.`HomeType` (`idHomeType`)
    ON DELETE SET NULL
    ON UPDATE CASCADE)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `airbnb`.`Calendar`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Calendar` (
  `BeginDate` DATE NOT NULL,
  `RentalPrice` DOUBLE NOT NULL,
  `EndDate` DATE NOT NULL,
  `ListingID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`BeginDate`, `EndDate`, `ListingID`),
  CONSTRAINT `ListingID`
    FOREIGN KEY (`ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `airbnb`.`Amenities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Amenities` (
  `idAmenities` INT NOT NULL,
  `Item` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idAmenities`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Listing_has_Amenities`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Listing_has_Amenities` (
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  `Amenities_idAmenities` INT NOT NULL,
  PRIMARY KEY (`Listing_ListingID`, `Amenities_idAmenities`),
  CONSTRAINT `fk_Listing_has_Amenities_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Listing_has_Amenities_Amenities1`
    FOREIGN KEY (`Amenities_idAmenities`)
    REFERENCES `airbnb`.`Amenities` (`idAmenities`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;



-- -----------------------------------------------------
-- Table `airbnb`.`Host_Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Host_Profile` (
  `idHost_Profile` INT NOT NULL,
  PRIMARY KEY (`idHost_Profile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Renter_Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Renter_Profile` (
  `idRenter_Profile` INT NOT NULL,
  PRIMARY KEY (`idRenter_Profile`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Users` (
  `SIN` INT NOT NULL,
  `Date of Birth` VARCHAR(45) NOT NULL,
  `Full Name` VARCHAR(45) NOT NULL,
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  PRIMARY KEY (`SIN`),
  CONSTRAINT `fk_User_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Renter_Profile1`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`User_has_Occupation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`User_has_Occupation` (
  `User_SIN` INT NOT NULL,
  `Occupation_idOccupation` INT NOT NULL,
  PRIMARY KEY (`User_SIN`, `Occupation_idOccupation`),
  CONSTRAINT `fk_User_has_Occupation_User1`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`User_Host_Listing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`User_Host_Listing` (
  `User_SIN` INT NOT NULL,
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_User_has_Listing_User1`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Listing_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`User_rent_Listing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`User_rent_Listing` (
  `User_SIN` INT NOT NULL,
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  `Credit Card` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_User_has_Listing_User2`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Listing_Listing2`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Address` (
  `Postal Code` INT NOT NULL,
  `City` VARCHAR(45) NOT NULL,
  `Country` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Postal Code`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Address_has_Listing`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Address_has_Listing` (
  `Address_Postal Code` INT NOT NULL,
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Address_Postal Code`, `Listing_ListingID`),
  CONSTRAINT `fk_Address_has_Listing_Address1`
    FOREIGN KEY (`Address_Postal Code`)
    REFERENCES `airbnb`.`Address` (`Postal Code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Address_has_Listing_Listing1`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Experience`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Experience` (
  `idExperience` INT NOT NULL,
  `Experience` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idExperience`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`User_has_Address`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`User_has_Address` (
  `User_SIN` INT NOT NULL,
  `Address_Postal Code` INT NOT NULL,
  PRIMARY KEY (`User_SIN`, `Address_Postal Code`),
  CONSTRAINT `fk_User_has_Address_User1`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Address_Address1`
    FOREIGN KEY (`Address_Postal Code`)
    REFERENCES `airbnb`.`Address` (`Postal Code`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `airbnb`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Rating` (
  `User_Host_Listing_User_SIN` INT NOT NULL,
  `User_Host_Listing_Listing_ListingID` VARCHAR(45) NOT NULL,
  `User_rent_Listing_User_SIN` INT NOT NULL,
  `User_rent_Listing_Listing_ListingID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_Host_Listing_User_SIN`, `User_Host_Listing_Listing_ListingID`, `User_rent_Listing_User_SIN`, `User_rent_Listing_Listing_ListingID`),
  CONSTRAINT `fk_User_Host_Listing_has_User_rent_Listing_User_Host_Listing1`
    FOREIGN KEY (`User_Host_Listing_User_SIN` , `User_Host_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`User_Host_Listing` (`User_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_Host_Listing_has_User_rent_Listing_User_rent_Listing1`
    FOREIGN KEY (`User_rent_Listing_User_SIN` , `User_rent_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`User_rent_Listing` (`User_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Comment` (
  `idComment` INT NOT NULL,
  `User_Host_Listing_User_SIN` INT NOT NULL,
  `User_Host_Listing_Listing_ListingID` VARCHAR(45) NOT NULL,
  `User_rent_Listing_User_SIN` INT NOT NULL,
  `User_rent_Listing_Listing_ListingID` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idComment`),
  CONSTRAINT `fk_Comment_User_Host_Listing1`
    FOREIGN KEY (`User_Host_Listing_User_SIN` , `User_Host_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`User_Host_Listing` (`User_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Comment_User_rent_Listing1`
    FOREIGN KEY (`User_rent_Listing_User_SIN` , `User_rent_Listing_Listing_ListingID`)
    REFERENCES `airbnb`.`User_rent_Listing` (`User_SIN` , `Listing_ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Comment_On_Renter_Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Comment_On_Renter_Profile` (
  `Comment_idComment` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Renter_Profile_User_SIN` INT NOT NULL,
  PRIMARY KEY (`Comment_idComment`, `Renter_Profile_idRenter_Profile`, `Renter_Profile_User_SIN`),
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

-- -----------------------------------------------------
-- Table `airbnb`.`Comment_On_Host_Profile`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Comment_On_Host_Profile` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Host_Profile_User_SIN` INT NOT NULL,
  `Comment_idComment` INT NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `Host_Profile_User_SIN`, `Comment_idComment`),
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

-- -----------------------------------------------------
-- Table `airbnb`.`Profile Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Profile Comments` (
  `User_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `Comment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_SIN`, `Renter_Profile_idRenter_Profile`, `Host_Profile_idHost_Profile`),
  CONSTRAINT `fk_User_has_Renter_Profile_User1`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Renter_Profile_Renter_Profile1`
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

-- -----------------------------------------------------
-- Table `airbnb`.`Listing Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Listing Comments` (
  `User_SIN` INT NOT NULL,
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  `Comment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_User_has_Listing_User3`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Listing_Listing3`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Host Comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Host Comments` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `User_SIN` INT NOT NULL,
  `Comment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `User_SIN`),
  CONSTRAINT `fk_Host_Profile_has_User_Host_Profile1`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Host_Profile_has_User_User1`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Renter Comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Renter Comment` (
  `User_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Comment` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`User_SIN`, `Renter_Profile_idRenter_Profile`),
  CONSTRAINT `fk_User_has_Renter_Profile_User2`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Renter_Profile_Renter_Profile2`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Listing Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Listing Rating` (
  `User_SIN` INT NOT NULL,
  `Listing_ListingID` VARCHAR(45) NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`User_SIN`, `Listing_ListingID`),
  CONSTRAINT `fk_User_has_Listing_User4`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Listing_Listing4`
    FOREIGN KEY (`Listing_ListingID`)
    REFERENCES `airbnb`.`Listing` (`ListingID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Renter Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Renter Rating` (
  `User_SIN` INT NOT NULL,
  `Renter_Profile_idRenter_Profile` INT NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`User_SIN`, `Renter_Profile_idRenter_Profile`),
  CONSTRAINT `fk_User_has_Renter_Profile_User3`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Renter_Profile_Renter_Profile3`
    FOREIGN KEY (`Renter_Profile_idRenter_Profile`)
    REFERENCES `airbnb`.`Renter_Profile` (`idRenter_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

-- -----------------------------------------------------
-- Table `airbnb`.`Host Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `airbnb`.`Host Rating` (
  `Host_Profile_idHost_Profile` INT NOT NULL,
  `User_SIN` INT NOT NULL,
  `Rating` INT NOT NULL,
  PRIMARY KEY (`Host_Profile_idHost_Profile`, `User_SIN`),
  CONSTRAINT `fk_Host_Profile_has_User_Host_Profile2`
    FOREIGN KEY (`Host_Profile_idHost_Profile`)
    REFERENCES `airbnb`.`Host_Profile` (`idHost_Profile`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Host_Profile_has_User_User2`
    FOREIGN KEY (`User_SIN`)
    REFERENCES `airbnb`.`Users` (`SIN`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;