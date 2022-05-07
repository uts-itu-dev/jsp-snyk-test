<%-- 
	Document   : Landing
	Created on : Mar 31, 2022, 5:39:53 PM
	Author     : Michael Wu
	
	Purpose	   : The main page of IoTBay.
--%>

<%@page import="Model.IoTBay.Person.Staff"%>
<%@page import="Model.IoTBay.Person.User" %>
<%@page import="Model.IoTBay.Person.Customer" %>
<%@page import="Model.IoTBay.Person.Address" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>IoTBay | Home</title>
		<meta http-equiv="refresh" content="1.5; URL=../index.jsp" />
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../Logout">Logout</a></div>
					<div class="navLinks right"><a href="Profile.jsp">Profile</a></div>
				</div>
			</nav>

			<div>
				<div class="centreScreen">

					<%
						try {
							Customer active = (Customer) session.getAttribute("User");
							if (active != null) {
								String firstName = active.getFirstName();
								String lastName = active.getLastName();
								Address address = active.getAddress();

								if (firstName != null && lastName != null) {
									out.println("<h1>Hello, " + firstName + " " + lastName + "!</h1>");
								} else {
									out.println("<h1>Hello!</h1>");
								}

								out.println("<br><p class=\"text\">");
								out.println("Your E-Mail Address is: " + active.getEmail());
								out.println("<br>");
								out.println("Your Password is: " + active.getPassword());

								if (address != null) {
									out.println("<br>");
									out.println("Your Address is: " + address);
								}

								out.println("</p>");
							} else {
								throw new NullPointerException("Landing.jsp::User is null");
							}
						} catch (ClassCastException c) {
							Staff active = (Staff) session.getAttribute("User");
							if (active != null) {
								String firstName = active.getFirstName();
								String lastName = active.getLastName();
								
								if (firstName != null && lastName != null) {
									out.println("<h1>Hello, " + firstName + " " + lastName + "!</h1>");
								} else {
									out.println("<h1>Hello!</h1>");
								}

								out.println("<br><p class=\"text\">");
								out.println("Your E-Mail Address is: " + active.getEmail());
								out.println("<br>");
								out.println("Your Password is: " + active.getPassword());
							}
						}
					%>
				</div
			</div>
		</div>
	</body>
</html>
