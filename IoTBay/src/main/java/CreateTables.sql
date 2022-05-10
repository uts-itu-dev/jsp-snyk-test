/**
 * Author:  Michael Wu
 * Created: May 4, 2022
 */

CREATE TABLE CUSTOMERS
(
	EMAIL VARCHAR(100) NOT NULL PRIMARY KEY,
	FIRSTNAME VARCHAR(100),
	LASTNAME VARCHAR(100),
	PASSWORD VARCHAR(100),
	STREETNUMBER VARCHAR(10),
	STREETNAME VARCHAR(100),
	SUBURB VARCHAR(100),
	POSTCODE VARCHAR(10),
	CITY VARCHAR(100),
	PHONENUMBER VARCHAR(15),
	CARDNUMBER VARCHAR(25),
	CVV VARCHAR(5),
	CARDHOLDER VARCHAR(200)
);

CREATE TABLE STAFF
(
	EMAIL VARCHAR(100) NOT NULL PRIMARY KEY,
	FIRSTNAME VARCHAR(100),
	LASTNAME VARCHAR(100),
	PASSWORD VARCHAR(100)
);

CREATE TABLE PRODUCTS
(
	PRODUCTID integer NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	PRODUCTNAME VARCHAR(100),
	DESCRIPTION VARCHAR(500),
	PRICE float
);

CREATE TABLE ORDERLINEITEM
(
	OWNER VARCHAR(100) NOT NULL,
	PRODUCTID integer NOT NULL,
	QUANTITY integer,

	CONSTRAINT OLI_PK PRIMARY KEY (OWNER, PRODUCTID)
);