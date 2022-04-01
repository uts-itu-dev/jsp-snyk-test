<%-- 
	Document   : index
	Created on : Mar 30, 2022, 5:59:57 PM
	Author     : Michael Wu

	Purpose    : The landing page of IoTBay.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IoTBay</title>
		<link rel="stylesheet" href="IoTCore/IoTBayStyles.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="index.jsp">Home</a></div>
					<%
						if (session.getAttribute("User") != null) {
							out.println("<div class=\"navLinks right\"><a href=\"Logout\">Logout</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"IoTCore/Profile.jsp\">Profile</a></div>");
						} else {
							out.println("<div class=\"navLinks right\"><a href=\"IoTCore/Register.jsp\">Register</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"IoTCore/Login.jsp\">Login</a></div>");
						}
					%>
				</div>
			</nav>

			<h1 class="IndexH1 ">IoTBay</h1>

			<!-- Content area. -->
			<div>
				<p class="text textArea">
					IoT Bay | Introduction to Software Development (41025) - Assignment 1: R0
					<br><br>
					The Internet of Things Store (IoTBay) is a small company based in Sydney, Australia.
					IoTBay wants to develop an online IoT devices ordering application to allow their
					customers to purchase IoT devices (e.g., sensors, actuators, gateways).
				</p>
				<br><br>
				<p class="text textArea left">
					IoTBay was developed in accordance to the requirements outlined by the University of
					Technology Sydney | 41025 - Intro. to Software Development.
					<br><br>
					<span class="pink">SEUNGWON OCK</span> | <span class="darkerPink">14109641</span>
					<br>
					<span class="pink">MICHAEL WU</span> | <span class="darkerPink">13938903</span>
					<br>
					<span class="pink">JERRY YAU</span> | <span class="darkerPink">14150371</span>
					<br>
					<span class="pink">CHRISTIAN WU</span> | <span class="darkerPink">14147817</span>
					<br>
					<span class="pink">YESEUL SHIN</span> | <span class="darkerPink">13978248</span>
					<br>
					<span class="pink">SAOBAN SALWA HABIB</span> | <span class="darkerPink">14104638</span>
				</p>
			</div>
		</div>

	</body>
</html>

