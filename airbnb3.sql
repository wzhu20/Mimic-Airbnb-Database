-- MySQL Workbench Forward Engineering

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS airbnb;
-- -----------------------------------------------------
-- Schema new_schema1
-- -----------------------------------------------------
USE airbnb;

-- -----------------------------------------------------
-- Table HomeType
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS HomeType (
  idHomeType INT NOT NULL,
  Type VARCHAR(45) NOT NULL,
  PRIMARY KEY (idHomeType))
;


-- -----------------------------------------------------
-- Table Calendar
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Calendar (
  DaysOfYearAvailable INT NOT NULL,
  RentalPrice VARCHAR(45) NOT NULL,
  PRIMARY KEY (DaysOfYearAvailable))
;


-- -----------------------------------------------------
-- Table Listing
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Listing (
  Latitutude VARCHAR(45) NOT NULL,
  Longitude VARCHAR(45) NOT NULL,
  ListingID VARCHAR(45) NOT NULL,
  HomeType_idHomeType INT NOT NULL,
  Calendar_DaysOfYearAvailable INT NOT NULL,
  PRIMARY KEY (ListingID),
  CONSTRAINT fk_Listing_HomeType1
    FOREIGN KEY (HomeType_idHomeType)
    REFERENCES HomeType (idHomeType)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Listing_Calendar1
    FOREIGN KEY (Calendar_DaysOfYearAvailable)
    REFERENCES Calendar (DaysOfYearAvailable)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);



-- -----------------------------------------------------
-- Table Amenities
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Amenities (
  idAmenities INT NOT NULL,
  Item VARCHAR(45) NOT NULL,
  PRIMARY KEY (idAmenities))
;


-- -----------------------------------------------------
-- Table Listing_has_Amenities
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Listing_has_Amenities (
  Listing_ListingID VARCHAR(45) NOT NULL,
  Amenities_idAmenities INT NOT NULL,
  PRIMARY KEY (Listing_ListingID, Amenities_idAmenities),
  CONSTRAINT fk_Listing_has_Amenities_Listing1
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Listing_has_Amenities_Amenities1
    FOREIGN KEY (Amenities_idAmenities)
    REFERENCES Amenities (idAmenities)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Host_Profile
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Host_Profile (
  idHost_Profile INT NOT NULL,
  PRIMARY KEY (idHost_Profile))
;


-- -----------------------------------------------------
-- Table Renter_Profile
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Renter_Profile (
  idRenter_Profile INT NOT NULL,
  PRIMARY KEY (idRenter_Profile))
;


-- -----------------------------------------------------
-- Table User
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS User (
  SIN INT NOT NULL,
  Date_of_Birth VARCHAR(45) NOT NULL,
  Full_Name VARCHAR(45) NOT NULL,
  Host_Profile_idHost_Profile INT NOT NULL,
  Renter_Profile_idRenter_Profile INT NOT NULL,
  PRIMARY KEY (SIN),
  CONSTRAINT fk_User_Host_Profile1
    FOREIGN KEY (Host_Profile_idHost_Profile)
    REFERENCES Host_Profile (idHost_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_Renter_Profile1
    FOREIGN KEY (Renter_Profile_idRenter_Profile)
    REFERENCES Renter_Profile (idRenter_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Occupation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Occupation (
  idOccupation INT NOT NULL,
  Job VARCHAR(45) NOT NULL,
  PRIMARY KEY (idOccupation))
;


-- -----------------------------------------------------
-- Table User_has_Occupation
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS User_has_Occupation (
  User_SIN INT NOT NULL,
  Occupation_idOccupation INT NOT NULL,
  PRIMARY KEY (User_SIN, Occupation_idOccupation),
  CONSTRAINT fk_User_has_Occupation_User1
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Occupation_Occupation1
    FOREIGN KEY (Occupation_idOccupation)
    REFERENCES Occupation (idOccupation)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table User_Host_Listing
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS User_Host_Listing (
  User_SIN INT NOT NULL,
  Listing_ListingID VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_SIN, Listing_ListingID),
  CONSTRAINT fk_User_has_Listing_User1
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Listing_Listing1
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table User_rent_Listing
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS User_rent_Listing (
  User_SIN INT NOT NULL,
  Listing_ListingID VARCHAR(45) NOT NULL,
  Credit_Card VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_SIN, Listing_ListingID),
  CONSTRAINT fk_User_has_Listing_User2
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Listing_Listing2
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Address (
  Postal_Code INT NOT NULL,
  City VARCHAR(45) NOT NULL,
  Country VARCHAR(45) NOT NULL,
  PRIMARY KEY (Postal_Code))
;


-- -----------------------------------------------------
-- Table Address_has_Listing
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Address_has_Listing (
  Address_Postal_Code INT NOT NULL,
  Listing_ListingID VARCHAR(45) NOT NULL,
  PRIMARY KEY (Address_Postal_Code, Listing_ListingID),
    FOREIGN KEY (Address_Postal_Code)
    REFERENCES Address (Postal_Code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Address_has_Listing_Listing1
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Experience
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Experience (
  idExperience INT NOT NULL,
  Experience VARCHAR(45) NOT NULL,
  PRIMARY KEY (idExperience))
;


-- -----------------------------------------------------
-- Table User_has_Address
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS User_has_Address (
  User_SIN INT NOT NULL,
  Address_Postal_Code INT NOT NULL,
  PRIMARY KEY (User_SIN, Address_Postal_Code),
  CONSTRAINT fk_User_has_Address_User1
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Address_Address1
    FOREIGN KEY (Address_Postal_Code)
    REFERENCES Address (Postal_Code)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Rating
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Rating (
  User_Host_Listing_User_SIN INT NOT NULL,
  User_Host_Listing_Listing_ListingID VARCHAR(45) NOT NULL,
  User_rent_Listing_User_SIN INT NOT NULL,
  User_rent_Listing_Listing_ListingID VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_Host_Listing_User_SIN, User_Host_Listing_Listing_ListingID, User_rent_Listing_User_SIN, User_rent_Listing_Listing_ListingID),
  CONSTRAINT fk_User_Host_Listing_has_User_rent_Listing_User_Host_Listing1
    FOREIGN KEY (User_Host_Listing_User_SIN , User_Host_Listing_Listing_ListingID)
    REFERENCES User_Host_Listing (User_SIN , Listing_ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_Host_Listing_has_User_rent_Listing_User_rent_Listing1
    FOREIGN KEY (User_rent_Listing_User_SIN , User_rent_Listing_Listing_ListingID)
    REFERENCES User_rent_Listing (User_SIN , Listing_ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Comment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Comment (
  idComment INT NOT NULL,
  User_Host_Listing_User_SIN INT NOT NULL,
  User_Host_Listing_Listing_ListingID VARCHAR(45) NOT NULL,
  User_rent_Listing_User_SIN INT NOT NULL,
  User_rent_Listing_Listing_ListingID VARCHAR(45) NOT NULL,
  PRIMARY KEY (idComment),
  CONSTRAINT fk_Comment_User_Host_Listing1
    FOREIGN KEY (User_Host_Listing_User_SIN , User_Host_Listing_Listing_ListingID)
    REFERENCES User_Host_Listing (User_SIN , Listing_ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comment_User_rent_Listing1
    FOREIGN KEY (User_rent_Listing_User_SIN , User_rent_Listing_Listing_ListingID)
    REFERENCES User_rent_Listing (User_SIN , Listing_ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Comment_On_Renter_Profile
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Comment_On_Renter_Profile (
  Comment_idComment INT NOT NULL,
  Renter_Profile_idRenter_Profile INT NOT NULL,
  Renter_Profile_User_SIN INT NOT NULL,
  PRIMARY KEY (Comment_idComment, Renter_Profile_idRenter_Profile, Renter_Profile_User_SIN),
  CONSTRAINT fk_Comment_has_Renter_Profile_Comment1
    FOREIGN KEY (Comment_idComment)
    REFERENCES Comment (idComment)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comment_has_Renter_Profile_Renter_Profile1
    FOREIGN KEY (Renter_Profile_idRenter_Profile)
    REFERENCES Renter_Profile (idRenter_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Comment_On_Host_Profile
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Comment_On_Host_Profile (
  Host_Profile_idHost_Profile INT NOT NULL,
  Host_Profile_User_SIN INT NOT NULL,
  Comment_idComment INT NOT NULL,
  PRIMARY KEY (Host_Profile_idHost_Profile, Host_Profile_User_SIN, Comment_idComment),
  CONSTRAINT fk_Host_Profile_has_Comment_Host_Profile1
    FOREIGN KEY (Host_Profile_idHost_Profile)
    REFERENCES Host_Profile (idHost_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Host_Profile_has_Comment_Comment1
    FOREIGN KEY (Comment_idComment)
    REFERENCES Comment (idComment)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table_Profile_Comments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Profile_Comments (
  User_SIN INT NOT NULL,
  Renter_Profile_idRenter_Profile INT NOT NULL,
  Host_Profile_idHost_Profile INT NOT NULL,
  Comment VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_SIN, Renter_Profile_idRenter_Profile, Host_Profile_idHost_Profile),
  CONSTRAINT fk_User_has_Renter_Profile_User1
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Renter_Profile_Renter_Profile1
    FOREIGN KEY (Renter_Profile_idRenter_Profile)
    REFERENCES Renter_Profile (idRenter_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Comments_On_Host_Profile1
    FOREIGN KEY (Host_Profile_idHost_Profile)
    REFERENCES Host_Profile (idHost_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Listing Comments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Listing_Comments (
  User_SIN INT NOT NULL,
  Listing_ListingID VARCHAR(45) NOT NULL,
  Comment VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_SIN, Listing_ListingID),
  CONSTRAINT fk_User_has_Listing_User3
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Listing_Listing3
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Host Comments
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Host_Comments (
  Host_Profile_idHost_Profile INT NOT NULL,
  User_SIN INT NOT NULL,
  Comment VARCHAR(45) NOT NULL,
  PRIMARY KEY (Host_Profile_idHost_Profile, User_SIN),
  CONSTRAINT fk_Host_Profile_has_User_Host_Profile1
    FOREIGN KEY (Host_Profile_idHost_Profile)
    REFERENCES Host_Profile (idHost_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Host_Profile_has_User_User1
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Renter Comment
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Renter_Comment (
  User_SIN INT NOT NULL,
  Renter_Profile_idRenter_Profile INT NOT NULL,
  Comment VARCHAR(45) NOT NULL,
  PRIMARY KEY (User_SIN, Renter_Profile_idRenter_Profile),
  CONSTRAINT fk_User_has_Renter_Profile_User2
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Renter_Profile_Renter_Profile2
    FOREIGN KEY (Renter_Profile_idRenter_Profile)
    REFERENCES Renter_Profile (idRenter_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Listing Rating
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Listing_Rating (
  User_SIN INT NOT NULL,
  Listing_ListingID VARCHAR(45) NOT NULL,
  Rating INT NOT NULL,
  PRIMARY KEY (User_SIN, Listing_ListingID),
  CONSTRAINT fk_User_has_Listing_User4
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Listing_Listing4
    FOREIGN KEY (Listing_ListingID)
    REFERENCES Listing (ListingID)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Renter Rating
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Renter_Rating (
  User_SIN INT NOT NULL,
  Renter_Profile_idRenter_Profile INT NOT NULL,
  Rating INT NOT NULL,
  PRIMARY KEY (User_SIN, Renter_Profile_idRenter_Profile),
  CONSTRAINT fk_User_has_Renter_Profile_User3
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_User_has_Renter_Profile_Renter_Profile3
    FOREIGN KEY (Renter_Profile_idRenter_Profile)
    REFERENCES Renter_Profile (idRenter_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;


-- -----------------------------------------------------
-- Table Host Rating
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS Host_Rating (
  Host_Profile_idHost_Profile INT NOT NULL,
  User_SIN INT NOT NULL,
  Rating INT NOT NULL,
  PRIMARY KEY (Host_Profile_idHost_Profile, User_SIN),
  CONSTRAINT fk_Host_Profile_has_User_Host_Profile2
    FOREIGN KEY (Host_Profile_idHost_Profile)
    REFERENCES Host_Profile (idHost_Profile)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_Host_Profile_has_User_User2
    FOREIGN KEY (User_SIN)
    REFERENCES User (SIN)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
;
