-----------------INTRODUCTION TO SOFTWARE DEVELOPMENT R0 (41025)-----------------
DEVELOPED BY:
	SAOBAN SALWA HABIB	| 14104638
	SEUNGWON OCK 		| 14109641
	YESEUL SHIN 		| 13978248
	CHRISTIAN WU 		| 14147817
	MICHAEL WU 		| 13938903
	JERRY YAU 		| 14150371

-----------------HOW TO RUN-----------------
1.  Download or clone this repository.
2.  Unzip the source code.
3.  Open NetBeams.
4.  Select File->Open Project.
5.  Navigate to the unzipped source code.
6.  Select 'IoTBay' and Open Project. It should have the 'Ma' Maven icon.
7.  Set this Project as the 'Main Project'.
8.  Select Tools->Servers.
9.  Remove any Servers you may have.
10. Select 'Add Server' and 'GlassFish Server'.
11. IoTBay was developed on and tested with a local installation of GlassFish Server 5.0.1
12. Download GlassFish 5.0.1
13. Select 'Next'.
14. Ensure that:

	Domain: domain1
	Host: localhost
	Target:
	Username: admin
	Password:

15. Select 'Finish' and 'Close'.
16. Clean and Build Main Project.
17. Run Main Project.

If a browser window doesn't automatically open, open your browser to: http://localhost:8080/IoTBay/ when the Project has been deployed.
IoTBay was tested using:

Google Chrome 100.0.4896.60 (Official Build)
Java Platform: jdk1.8.0_321