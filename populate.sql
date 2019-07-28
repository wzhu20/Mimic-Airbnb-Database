USE `airbnb`;
/* 15 hosts, 10 renters*/

INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);
INSERT INTO Host_Profile  VALUES(NULL);


INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);
INSERT INTO Renter_Profile  VALUES(NULL);

/* 20 address */
INSERT INTO address (Postal_code, city, country) VALUES(111111,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(222222,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(333333,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(444444,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(555555,'city1','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(666666,'city2','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(777777,'city2','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(888888,'city3','Country3');
INSERT INTO address (Postal_code, city, country) VALUES(999999,'city4','Country4');
INSERT INTO address (Postal_code, city, country) VALUES(100000,'city5','Country5');
INSERT INTO address (Postal_code, city, country) VALUES(200000,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(300000,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(400000,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(500000,'city1','Country1');
INSERT INTO address (Postal_code, city, country) VALUES(600000,'city1','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(700000,'city2','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(800000,'city2','Country2');
INSERT INTO address (Postal_code, city, country) VALUES(900000,'city3','Country3');
INSERT INTO address (Postal_code, city, country) VALUES(110000,'city4','Country4');
INSERT INTO address (Postal_code, city, country) VALUES(120000,'city5','Country5');

/* 10 dates in calendar */
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-01','2000-01-02');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-01','2000-01-03');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-03','2000-01-04');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-04','2000-01-05');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-05','2001-02-01');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-06','2002-03-01');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-07','2003-04-01');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-08','2000-05-20');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-09','2000-06-20');
INSERT INTO Calendar (BeginDate, EndDate) VALUES('2000-01-10','2000-07-21');

/* 20 users */
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(100000,'2000-01-01','a1',1,NULL,1);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(200000,'2000-01-01','a2',2,NULL,1);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(300000,'2000-01-01','a3',3,NULL,1);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(400000,'2000-01-01','a4',4,NULL,1);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(500000,'2000-01-01','a5',5,NULL,2);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(600000,'2000-01-01','a6',6,NULL,2);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(700000,'2000-01-01','a7',7,NULL,2);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(800000,'2000-01-01','a8',8,NULL,2);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(900000,'2000-01-01','a9',9,NULL,3);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(110000,'2000-01-01','a11',10,NULL,3);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(120000,'2000-01-01','a12',NULL,1,3);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(130000,'2000-01-01','a13',NULL,2,3);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(140000,'2000-01-01','a14',NULL,3,4);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(150000,'2000-01-01','a15',NULL,4,4);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(160000,'2000-01-01','a16',NULL,5,4);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(170000,'2000-01-01','a17',11,6,4);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(180000,'2000-01-01','a18',12,7,5);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(190000,'2000-01-01','a19',13,8,5);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(210000,'2000-01-01','a20',14,9,5);
INSERT INTO USERS(SIN, DATE_OF_BIRTH, Full_Name, Host_Profile_idHost_Profile,Renter_Profile_idRenter_Profile, OCCUPATION) VALUES(220000,'2000-01-01','a21',15,10,5);

INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(100000,100000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(110000,200000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(120000,300000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(130000,400000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(140000,500000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(150000,600000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(160000,700000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(170000,800000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(180000,900000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(190000,100000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(200000,200000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(210000,300000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(900000,400000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(200000,500000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(300000,600000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(400000,700000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(500000,800000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(600000,900000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(700000,100000);
INSERT INTO users_has_address (Users_SIN, address_postal_code) VALUES(800000,100000);

INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(1,1,1);
INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(20,20,2);
INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(300,300,3);
INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(40,40,4);
INSERT INTO Listing (Latitutude, Longitude, HomeType_idHomeType) VALUES(500,500,5);

INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(111111,1);
INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(222222,2);
INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(333333,3);
INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(444444,4);
INSERT INTO Address_has_Listing (Address_Postal_Code, Listing_ListingID) VALUES(555555,5);

INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate,Calendar_EndDate, RentalPrice) VALUES(1,'2000-01-01','2000-01-02',100);
INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate,Calendar_EndDate, RentalPrice) VALUES(2,'2000-01-01','2000-01-02',2000);
INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate,Calendar_EndDate, RentalPrice) VALUES(3,'2000-01-05','2001-02-01',3000);
INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate,Calendar_EndDate, RentalPrice) VALUES(4,'2000-01-07','2003-04-01',400);
INSERT INTO Listing_has_Calendar (Listing_ListingID, Calendar_BeginDate,Calendar_EndDate, RentalPrice) VALUES(5,'2000-01-10','2000-07-21',50);

INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(1,1);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(1,2);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(1,3);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(2,4);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(2,1);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(3,4);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(4,3);
INSERT INTO Listing_has_Amenities (Listing_ListingID, Amenities_idAmenities) VALUES(5,1);

INSERT INTO `Users_Host_Listing` (Users_SIN, Listing_ListingID) VALUES(100000, 1);
INSERT INTO `Users_Host_Listing` (Users_SIN, Listing_ListingID) VALUES(200000, 2);
INSERT INTO `Users_Host_Listing` (Users_SIN, Listing_ListingID) VALUES(300000, 3);
INSERT INTO `Users_Host_Listing` (Users_SIN, Listing_ListingID) VALUES(400000, 4);
INSERT INTO `Users_Host_Listing` (Users_SIN, Listing_ListingID) VALUES(500000, 5);

INSERT INTO `Users_rent_Listing` (Users_SIN, Listing_ListingID, Credit_Card) VALUES(120000, 1, 1234);
INSERT INTO `Users_rent_Listing` (Users_SIN, Listing_ListingID, Credit_Card) VALUES(130000, 2, 12345);
INSERT INTO `Users_rent_Listing` (Users_SIN, Listing_ListingID, Credit_Card) VALUES(140000, 3, 123456);
INSERT INTO `Users_rent_Listing` (Users_SIN, Listing_ListingID, Credit_Card) VALUES(150000, 4, 1234567);
INSERT INTO `Users_rent_Listing` (Users_SIN, Listing_ListingID, Credit_Card) VALUES(160000, 5, 12345678);

INSERT INTO `Renter Rating` (Users_SIN, Renter_Profile_idRenter_Profile, Rating) VALUES(120000,1, 1);
INSERT INTO `Renter Rating` (Users_SIN, Renter_Profile_idRenter_Profile, Rating) VALUES(130000,2, 2);
INSERT INTO `Renter Rating` (Users_SIN, Renter_Profile_idRenter_Profile, Rating) VALUES(140000,3, 3);
INSERT INTO `Renter Rating` (Users_SIN, Renter_Profile_idRenter_Profile, Rating) VALUES(150000,4, 4);
INSERT INTO `Renter Rating` (Users_SIN, Renter_Profile_idRenter_Profile, Rating) VALUES(160000,5, 5);

INSERT INTO `Renter Comment` (Users_SIN, Renter_Profile_idRenter_Profile, Comment) VALUES(120000,1, 'good');
INSERT INTO `Renter Comment` (Users_SIN, Renter_Profile_idRenter_Profile, Comment) VALUES(130000,2, 'bad');
INSERT INTO `Renter Comment` (Users_SIN, Renter_Profile_idRenter_Profile, Comment) VALUES(140000,3, 'good and bad');
INSERT INTO `Renter Comment` (Users_SIN, Renter_Profile_idRenter_Profile, Comment) VALUES(150000,4, 'good and good');
INSERT INTO `Renter Comment` (Users_SIN, Renter_Profile_idRenter_Profile, Comment) VALUES(160000,5, 'good good');

INSERT INTO `Host Rating` (Users_SIN, Host_Profile_idHost_Profile, Rating) VALUES(100000,1,  1);
INSERT INTO `Host Rating` (Users_SIN, Host_Profile_idHost_Profile, Rating) VALUES(110000,2,  2);
INSERT INTO `Host Rating` (Users_SIN, Host_Profile_idHost_Profile, Rating) VALUES(120000,3,  3);
INSERT INTO `Host Rating` (Users_SIN, Host_Profile_idHost_Profile, Rating) VALUES(130000,4,  4);
INSERT INTO `Host Rating` (Users_SIN, Host_Profile_idHost_Profile, Rating) VALUES(140000,5,  5);

INSERT INTO `Host Comments` (Users_SIN, Host_Profile_idHost_Profile, Comment) VALUES(100000,1, 'bad');
INSERT INTO `Host Comments` (Users_SIN, Host_Profile_idHost_Profile, Comment) VALUES(110000,2, 'bad and bad');
INSERT INTO `Host Comments` (Users_SIN, Host_Profile_idHost_Profile, Comment) VALUES(120000,3, 'good and bad');
INSERT INTO `Host Comments` (Users_SIN, Host_Profile_idHost_Profile, Comment) VALUES(130000,4, 'bad bad');
INSERT INTO `Host Comments` (Users_SIN, Host_Profile_idHost_Profile, Comment) VALUES(140000,5, 'good');

INSERT INTO `Listing Rating` (Users_SIN, Listing_ListingID, Rating) VALUES(120000,1, 1);
INSERT INTO `Listing Rating` (Users_SIN, Listing_ListingID, Rating) VALUES(130000,2, 2);
INSERT INTO `Listing Rating` (Users_SIN, Listing_ListingID, Rating) VALUES(140000,3, 3);
INSERT INTO `Listing Rating` (Users_SIN, Listing_ListingID, Rating) VALUES(150000,4, 4);
INSERT INTO `Listing Rating` (Users_SIN, Listing_ListingID, Rating) VALUES(160000,5, 5);

