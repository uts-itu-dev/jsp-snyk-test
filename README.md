## INTRODUCTION TO SOFTWARE DEVELOPMENT R1 (41025)

DEVELOPED BY:  

	SAOBAN SALWA HABIB | 14104638  
	SEUNGWON OCK | 14109641  
	YESEUL SHIN | 13978248  
	CHRISTIAN WU | 14147817  
	MICHAEL WU | 13938903  	
	JERRY YAU | 14150371  
	
## IoTBAY E-COMMERCE WEBSITE
This Project was made according to specifications provided by the University of Technology Sydney, Introduction to Software Development (41025).<br /><br />
The Internet of Things store (IoTBay) sells a number of devices, including, but not limited to: sensors, actuators, and gateways, to customers around Australia. At present, IoTBay does not provide customers to purchase IoT devices online. As such, IoTBay was an online website developed that allows customers an easier way to access, view and purchase their devices.<br /><br />
This website should facilitate the processing of online payments for customers, provide details for orders and deliveries, and let customers register an account to use with IoTBay. The new website should also provide an interface for existing staff members to manage the new IoTBay website system.


## HOW TO RUN

Download or clone this repository.<br />
Unzip the source code.<br />
Open NetBeams.<br />
Select File->Open Project.<br />
Navigate to the unzipped source code.<br />
Select 'IoTBay' and Open Project. It should have the 'Ma' Maven icon.<br />
Set this Project as the 'Main Project'.<br />
Select Tools->Servers.<br />
Remove any Servers you may have.<br />
Select 'Add Server' and 'GlassFish Server'.<br />
IoTBay was developed on and tested with a local installation of GlassFish Server 5.0.1<br />
Download GlassFish 5.0.1<br />
Select 'Next'.<br />
Ensure that:  

		Domain: domain1
		Host: localhost
		Target:
		Username: admin
		Password:
Select 'Finish' and 'Close'.<br />
Clean and Build Main Project.<br />
Run Main Project.<br />
If a browser window doesn't automatically open, open your browser to: http://localhost:8080/IoTBay/ when the Project has been deployed.<br />
IoTBay was tested using:  

	Google Chrome 100.0.4896.60 (Official Build)
	Java Platform: jdk1.8.0_321

