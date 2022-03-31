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

			<p class="IndexH1">IoTBay</p>
		</div>

	</body>
</html>

