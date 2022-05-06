/**
 * Author:  Michael Wu
 * Created: May 6, 2022
 */

INSERT INTO CUSTOMERS
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, STREETNUMBER, STREETNAME, SUBURB, POSTCODE, CITY, PHONENUMBER, CARDNUMBER, CVV, CARDHOLDER)
VALUES
('michael.wu-2@student.uts.edu.au'    , 'Michael', 'Wu'       , 'MWPassword', '1' , 'Red Rock'      , 'Mars'    , '0004', 'Solar System', '0470639692', '5127384725169458', '420', 'MR. MICHAEL WU'),
('p.sherman@nemo.com'                 , 'Philip' , 'Sherman'  , 'PSPassword', '42', 'Wallaby Way'   , 'Blaxland', '2774', 'Sydney'      , '0415054028', ''                , ''   , ''),
('winston.churchill@parliament.gov.uk', 'Winston', 'Churchill', 'WCPassowrd', '10', 'Downing Street', 'London'  , '2010', 'London'      , '6132443896', '4758293675927350', '104', 'SIR. WINSTON CHURCHILL');

INSERT INTO STAFF
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD)
VALUES
('sheev.palpatine@galactic.republic.com', 'Sheev'   , 'Palpatine', 'SPPassword'),
('j.e.hoover@fbi.gov'                   , 'J. Edgar', 'Hoover'   , 'JEHPassword'),
('ian.fleming@mi.six.gov'               , 'Ian'     , 'Fleming'  , 'IFPassword');

INSERT INTO PRODUCTS
(PRODUCTNAME, DESCRIPTION, PRICE)
VALUES
('Actuator'           , 'A hydraulically or electrically powered device that achieves physical movement through mechanical force.', 124.99),
('Router'             , 'A device that manages wireless connections and the sharing of information between connected devices in a local area network.', 79.99),
('Redstone Repeater'  , 'A remarkable feat of electrical engineering to repeat electrical currents to full source strength.', 29.99),
('PIR Motion Sensor'  , 'A power-efficient way to sense and detect motion through a field of view.', 34.99),
('FLIR Camera'        , 'A special camera designed to see differentiations in heat.', 649.99);