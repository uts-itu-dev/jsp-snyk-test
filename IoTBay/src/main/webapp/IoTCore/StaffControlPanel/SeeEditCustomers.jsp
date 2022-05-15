<%-- 
    Document   : SeeEditCustomers
    Created on : May 10, 2022, 3:25:56 PM
    Author     : Michael Wu
--%>

<%@page import="Model.IoTBay.Person.Customer"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>See / Edit Customers</title>
		<link rel="stylesheet" href="../IoTBayStyles.css">
	</head>
	<body>
		<!-- Force refresh of the Database. -->
		<%
			IoTWebpageBase.connectToDB();

			session.setAttribute("UDatabase", IoTWebpageBase.uDB);
			session.setAttribute("Customers", IoTWebpageBase.uDB.customers);
			session.setAttribute("Staff", IoTWebpageBase.uDB.staff);
			session.setAttribute("Products", IoTWebpageBase.uDB.products);
		%>

		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../../../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../../../Logout">Logout</a></div>
					<div class="navLinks right"><a href="../Profile.jsp">Profile</a></div>
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Registered Customers</h1>

				<!-- Show a message notifying the successful inclusion of a Product. -->
				<%
					String err = request.getParameter("err");
					if (err != null) {
						out.println("<p style=\"text-align:center;\"><span style=\"color:red\"><br>" + err + "</span></p>");
					}

					String upd = request.getParameter("upd");
					if (upd != null) {
						out.println("<p style=\"text-align:center;\"><span style=\"color:green\"><br>" + upd + "</span></p>");
					}
				%>

				<table>
					<tr>
						<th>Email</th>
						<th>First Name</th>
						<th>Last Name</th>
					</tr>


					<div class="form-container">
						<div class="form-inner">
							<%
								for (int i = 0; i < IoTWebpageBase.uDB.customers.size(); ++i) {

									// Initialise Vars.
									Customer c = IoTWebpageBase.uDB.customers.get(i);
									String email = c.getEmail();
									String fn = c.getFirstName();
									String ln = c.getLastName();

									// Begin HTML.
									out.println("<tr>");
									out.println("<form action=\"../../Update\" class=\"login\" method=\"POST\">");

									// Write Data.
									out.println("<td>" + email + "</td>");
									out.println("<td>" + fn + "</td>");
									out.println("<td>" + ln + "</td>");

									// Update Details, or Remove Customer?
									out.println("<td><div class=\"field\"><input class=\"button\" type=\"submit\" value=\"Update\" name=\"Update\"></div></td>");
									out.println("<td><div class=\"field\"><input class=\"button\" type=\"submit\" value=\"Remove\" name=\"Remove\"></div></td>");
									
									out.println("<input type=\"hidden\" name=\"Email\" value=\"" + email + "\">");

									// End HTML.
									out.println("</form>");
									out.println("</tr>");
								}
							%>
						</div>
					</div>
				</table>
			</div>
		</div>
	</body>
</html>
