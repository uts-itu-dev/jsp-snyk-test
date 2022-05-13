<%-- 
    Document   : Orders
    Created on : May 13, 2022, 4:23:44 PM
    Author     : Michael Wu
--%>

<%@page import="Model.IoTBay.Person.Address"%>
<%@page import="Model.IoTBay.OrderLineItem"%>
<%@page import="Model.IoTBay.Product"%>
<%@page import="Model.IoTBay.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="DAO.DBManager"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page import="Model.IoTBay.Person.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Orders</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
	</head>
	<body>

		<%
			User active = (User) session.getAttribute("User");
			if (active != null)
			{
				IoTWebpageBase.uDB.injectOrders(active.getEmail());
			}
			else
			{
				IoTWebpageBase.uDB.injectOrders(DBManager.AnonymousUserEmail);
			}

			session.setAttribute("Orders", IoTWebpageBase.uDB.orders);
		%>

		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<%
						if (active != null)
						{
							out.println("<div class=\"navLinks right\"><a href=\"../Logout\">Logout</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Profile.jsp\">Profile</a></div>");
						}
						else
						{
							out.println("<div class=\"navLinks right\"><a href=\"Register.jsp\">Register</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Login.jsp\">Login</a></div>");
						}
					%>
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Your Orders</h1>

				<!-- Notifications. -->
				<%
					out.println("<p style=\"text-align:center;\">");

					String err = request.getParameter("err");
					if (err != null)
					{
						out.println("<span style=\"color:red\"><br>" + err + "</span>");
					}

					String upd = request.getParameter("upd");
					if (upd != null)
					{
						out.println("<span style=\"color:green\"><br>" + upd + "</span>");
					}

					out.println("</p>");
				%>

				<c:if test="${Orders != null}">
					<table style="width:90%">
						<!-- Table Headings. -->
						<tr>
							<th>Order No.#</th>
							<th>Products</th>
							<th>Total Cost</th>
							<th>Status</th>
							<th>Address</th>
						</tr>

						<%
							DecimalFormat df = new DecimalFormat("0.00");
							ArrayList<Order> items = (ArrayList<Order>) session.getAttribute("Orders");
							for (int i = 0; i < items.size(); ++i)
							{
								// Initialise Variables.
								Order OLI = items.get(i);
								ArrayList<OrderLineItem> p = OLI.getProducts();
								float total = OLI.getTotalCost();
								String status = OLI.getStatus();

								// Begin HTML.
								out.println("<tr>");
								out.println("<form action=\"../CancelOrder\" class=\"login\" method=\"POST\">");

								// Order No.#.
								out.println("<td>" + (i + 1) + "</td>");

								// Products.
								String display = "";
								for (OrderLineItem auto : p)
								{
									display += auto.getQuantity() + "x&nbsp;";
									display += auto.getProduct().getName();
									display += "<br>";
								}
								out.println("<td>" + display + "</td>");

								// Total Price of Order.
								String price = df.format(total);
								out.println("<td>$" + price + "</td>");

								// Status.
								out.println("<td>" + status + "</td>");

								// Shipping Address.
								Address a = OLI.getAddress();
								String addr = a.getNumber() + " " + a.getStreetName();
								addr += ",<br>" + a.getSuburb() + ", " + a.getPostcode();
								addr += ",<br>" + a.getCity();
								out.println("<td>" + addr + "</td>");

								// Cancel Order?
								out.println("<td><p>cancel works, but it doesnt add the stock/quantities back into IoTBay :( </p><div class=\"field\"><input class=\"button\" type=\"submit\" value=\"Cancel\" name=\"Cancel\"></div></td>");

								// Get OrderID.
								int oid = OLI.getID();
								out.println("<input type=\"hidden\" name=\"oid\" value=\"" + oid + "\">");

								// Get Owner
								String email = active == null
									? DBManager.AnonymousUserEmail
									: OLI.getOwner().getEmail();
								out.println("<input type=\"hidden\" name=\"owner\" value=\"" + email + "\">");

								// End HTML.
								out.println("</div>");
								out.println("</form>");
								out.println("</tr>");
							}
						%>
					</table>
				</c:if>
			</div>
		</div>
	</body>
</html>
