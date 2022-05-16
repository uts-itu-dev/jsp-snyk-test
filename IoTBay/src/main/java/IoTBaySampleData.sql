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
('Hextech GLP-800'    , 'A device which shoots icy spiky stuff which freezes whatever it hits.'                                                       , 2800   , 2),
('Wireless IP Camera' , 'A camera that can be controlled and operated over the internet. Commonly referred to as CCTV cameras, but creepier'          , 48.99  , 3),
('NAND Gate'          , 'A piece of electronic hardware which will only produce a current if two input currents do not match each other. Not AND.'    , 4.99   , 1250),
('XOR Gate'           , 'A piece of electronic hardware which will only produce a current if two input currents are different. EXclusive OR.'         , 4.99   , 2500),
('AND Gate'           , 'A piece of electronic hardware which will only produce a current if two input currents are the same. AND.'                   , 2.49   , 5217),
('OR Gate'            , 'A piece of electronic hardware which will only produce a current if at least one input current is on. OR.'                   , 2.49   , 6782),
('NOR Gate'           , 'A piece of electronic hardware which will only produce a current if two input currents are off. Not OR.'                     , 2.49   , 4811),
('Shure Microphone'   , 'A device that captures analogue sound waves and converts them into a digital signal.'                                        , 179.99 , 174),
('Google Home'        , 'A device that connects to your house and controls it without you knowing. This device spies on you while you sleep.'         , 129.99 , 650),
('Smart Lightbulb'    , 'A very not-useless lightbulb that can be controlled through the internet, such as a Google Home device.'                     , 64.99  , 423),
('Wireless Charger'   , 'An ineffecient means to charge a device that supports wireless charging. Energy is wasted on heat and charges very slowly'   , 24.99  , 387),
('Nexus 6P'           , 'The smartest smartphone to have ever been built. The sheer depth of power and knowledge is so dense, it generates gravity'   , 847.99 , 1),
('NVIDIA RTX 3090 Ti' , 'An unbelievably powerful graphics processing unit that sucks 400 kWh from your energy bills. Unnecessarily powerful.'        , 2899.99, 0),
('ITZYs Lightring'    , 'DDA-DDA-LA-DDA-LA-DDA-LA | PEOPLE LOOK AT ME, AND THEY TELL ME | OEMOMAN BOGO NAEGA NALLARI GATDAEYO'                        , 62.49  , 2019),
('Unreal Engine 5'    , 'The worlds most open and powerful real-time 3D digital creation software. Known for the best games, like Fornite /s.'        , 0.00   , 9999),
('Unity Engine'       , 'A game engine predominantly used for producing 2D games. Wonderful for making Pacman: https://wichael-mu.itch.io/pac-man'    , 100000 , 9999),
('Double Cheesebuger' , 'Two 100% pure beef burger patties seasoned with a pinch of salt and pepper, separated by two slices of American Cheese.'     , 5.35   , 4637),
('Wireless Scales'    , 'A device used to wirelessly measure the weight of something, usually people who eat a lot of Double Cheeseburgers.'          , 19.99  , 4637),
('Redstone'           , 'The base item required to generate electricity. It allowed humans to steam through the Industrial Revolution.'               , 0.99   , 64),
('The Helicarrier'    , 'A REAL flying aircraft carrier used by the Strategic Homeland Intervention, Enforcement and Logistics Division.'             , 23.99  , 1),
('Lightsabre'         , 'The weapon commonly used by any force-sensitive being. An incredibly powerful sword that can cut almost anything.'           , 3000   , 4),
('Hubble Telescope'   , 'The space telescope responsible for capturing the universe and letting smart people better understand our universe.'         , 1600   , 1),
('F2004'              , 'Considered to be the most fastest racecar in the world - 30 podiums in 20 races; giving world titles to MSC and Ferrari.'    , 7583.99, 2);

INSERT INTO ADMINISTRATOR
(EMAIL, FIRSTNAME, LASTNAME, PASSWORD)
VALUES
('admin@iotbay.com', 'System', 'Admin', 'admin');