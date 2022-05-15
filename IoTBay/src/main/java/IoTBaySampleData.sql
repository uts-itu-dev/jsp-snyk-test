/**
 * Author:  Michael Wu
 * Created: May 6, 2022
 */

INSERT INTO CUSTOMERS
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD, STREETNUMBER, STREETNAME, SUBURB, POSTCODE, CITY, PHONENUMBER, CARDNUMBER, CVV, CARDHOLDER)
VALUES
--               EMAIL                   FIRST        LAST        PASSWORD  ST. NUM     ST. NAME         SUBURB     POSTCODE        CITY         PHONE NO.         CARD NO        CVV    CARD HOLDER.
('michael.wu-2@student.uts.edu.au'    , 'Michael', 'Wu'       , 'MWPassword', '1'    , 'Red Rock'      , 'Mars'    , '0004'   , 'Solar System', '0470639692', '5127384725169458', '420', 'MR. MICHAEL WU'),
('p.sherman@nemo.com'                 , 'Philip' , 'Sherman'  , 'PSPassword', '42'   , 'Wallaby Way'   , 'Blaxland', '2774'   , 'Sydney'      , '0415054028', ''                , ''   , ''),
('winston.churchill@parliament.gov.uk', 'Winston', 'Churchill', 'WCPassowrd', '10'   , 'Downing Street', 'London'  , '2010'   , 'London'      , '6132443896', '4758293675927350', '104', 'SIR. WINSTON CHURCHILL');

INSERT INTO STAFF
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD)
VALUES
--               EMAIL                   FIRST        LAST        PASSWORD
('sheev.palpatine@galactic.republic.com', 'Sheev'   , 'Palpatine', 'SPPassword'),
('j.e.hoover@fbi.gov'                   , 'J. Edgar', 'Hoover'   , 'JEHPassword'),
('ian.fleming@mi.six.gov'               , 'Ian'     , 'Fleming'  , 'IFPassword');

INSERT INTO PRODUCTS
(PRODUCTNAME, DESCRIPTION, PRICE, STOCK)
VALUES
--   NAME    Max -> |                   DESCRIPTION                                                                      DESCRIPTION          Max -> |   PRICE    STOCK
('Actuator'           , 'A hydraulically or electrically powered device that achieves physical movement through mechanical force.'                    , 124.99 , 250),
('Router'             , 'A device that manages wireless connections and the sharing of information between connected devices in a local area network.', 79.99  , 70),
('Redstone Repeater'  , 'A remarkable feat of electrical engineering to repeat electrical currents to full source strength.'                          , 29.99  , 64),
('PIR Motion Sensor'  , 'A power-efficient way to sense and detect motion through a field of view.'                                                   , 34.99  , 570),
('FLIR Camera'        , 'A special camera designed to see differentiations in heat.'                                                                  , 649.99 , 15),
('HP Spectre x360'    , 'A convertible ultrabook laptop/tablet with an 10th Generation Intel i7 Processor and a 60,000 Wh battery rated for 20 hours.', 2048   , 2),
('Barcode Scanner'    , 'A device fitted with optics capable of reading and decoding barcodes.'                                                       , 44.99  , 30),
('Raspberry Pi'       , 'A minature computer that can run code and act as a website server relayer. Found here at UTS!'                               , 54.99  , 69),
('Ti-82 Calculator'   , 'A very powerful graphing calculator with a whopping 28 kB RAM and a 96x64 screen resolution.'                                , 125.00 , 82),
('iPhone 11 Pro Max'  , 'A midnight green iPhone with 256 GB storage with a 6.5" Super Retina XDR OLED display - $1846.99 down the drain!'            , 1846.99, 11),
('Hextech GLP-800'    , 'A device which shoots icy spiky stuff which freezes whatever it hits.'                                                       , 2800   , 2);

INSERT INTO ADMINISTRATOR
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD)
VALUES
('admin@iotbay.com', 'System', 'Admin', 'admin');