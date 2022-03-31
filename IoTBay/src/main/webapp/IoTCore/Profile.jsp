<%-- 
	Document   : Profile
	Created on : Mar 31, 2022, 6:05:20 PM
	Author     : Michael Wu

	Purpose    : Allow the logged in user to see their profile.
--%>

<%@page import="Model.IoTBay.Person.User"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>JSP Page</title>
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Your Profile</h1>
				<%
					User active = (User) session.getAttribute("User");
					if (active != null) {
						out.println(active.getFirstName() + " " + active.getLastName());
						out.println("<br>");
						out.println(active.getEmail());
					}
				%>
			</div>
		</div>
	</body>
</html>
