<%-- 
	Document   : Landing
	Created on : Mar 31, 2022, 5:39:53 PM
	Author     : Michael Wu
	
	Purpose	   : The main page of IoTBay.
--%>

<%@page import="Model.IoTBay.Person.User" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>IoTBay | Home</title>
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../Logout">Logout</a></div>
				</div>
			</nav>

			<div>
				<div class="centreScreen">
					<h1>Hello,
						<%
							User active = (User) session.getAttribute("User");
							if (active != null) {
								out.println(active.getFirstName() + " " + active.getLastName() + "!");
								out.println("<br>");
								out.println("Your E-Mail Address is: " + active.getEmail());
								out.println("<br>");
								out.println("Your Password is: " + active.getPassword());
								out.println("<br>");
								out.println("Your Address is: " + active.getAddress());
							} else {
								throw new NullPointerException("User is null");
							}
						%>
					</h1>
				</div
			</div>
		</div>
	</body>
</html>
