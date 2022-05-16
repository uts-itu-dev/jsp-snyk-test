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
						out.println("<div class=\"navLinks right\"><a href=\"Cart.jsp\">Cart</a></div>");
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

				<!-- if (request.getAttribute("Orders") != null) -->
				<c:if test="${Orders != null}">
					<%
						DecimalFormat df = new DecimalFormat("0.00");
						ArrayList<Order> items = (ArrayList<Order>) session.getAttribute("Orders");
						if (items.isEmpty())
						{
							out.println("<h2>You haven't ordered anything yet!</h2>");
							out.println("<meta http-equiv=\"refresh\" content=\"1.5; URL=../index.jsp#Products\"/>");
						}
						else
						{ // <- I didn't know you could do this.
					%>
					<table style="width:90%">
						<!-- Table Headings. -->
						<tr>
							<th>Order No.#</th>
							<th>Products</th>
							<th>Total Cost</th>
							<th>Status</th>
							<th>Address</th>
							<th>Date Purchased</th>
						</tr>

						<%
								for (int i = 0; i < items.size(); ++i)
								{
									// Initialise Variables.
									Order o = items.get(i);
									ArrayList<OrderLineItem> p = o.getProducts();
									float total = o.getTotalCost();
									String status = o.getStatus();
									boolean bMarkMainReturn = false;

									String colourDependingOnStatus = status.equals("Cancelled") ? "#777777" : "#000000";


									// Products.
									String display = "";
									for (OrderLineItem auto : p)
									{
										Product product = auto.getProduct();
										if (product != null && product.getName().length() > 0)
										{
											display += auto.getQuantity() + "x&nbsp;";
											display += product.getName();
											display += "<br>";
										}
										else
										{
											bMarkMainReturn = true;
										}
									}

									if (bMarkMainReturn)
									{
										continue;
									}
									
									// Begin HTML.
									out.println("<tr>");
									out.println("<form action=\"../CancelOrder\" class=\"login\" method=\"POST\">");

									// Order No.#.
									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">" + (i + 1) + "</td>");

									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">" + display + "</td>");

									// Total Price of Order.
									String price = df.format(total);
									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">$" + price + "</td>");

									// Status.
									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">" + status + "</td>");

									// Shipping Address.
									Address a = o.getAddress();
									String addr = a.getNumber() + " " + a.getStreetName();
									addr += ",<br>" + a.getSuburb() + ", " + a.getPostcode();
									addr += ",<br>" + a.getCity();
									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">" + addr + "</td>");

									// Date Purchased.
									out.println("<td style=\"color:" + colourDependingOnStatus + ";\">" + o.getPurchaseDateOnly() + "</td>");

									if (status.equals("Confirmed"))
									{
										// Cancel Order?
										out.println("<td><div class=\"field\"><input class=\"button\" type=\"submit\" value=\"Cancel\" name=\"Cancel\"></div></td>");
									}

									// Get OrderID.
									int oid = o.getID();
									out.println("<input type=\"hidden\" name=\"oid\" value=\"" + oid + "\">");

									// Get Owner
									String email = active == null
										? DBManager.AnonymousUserEmail
										: o.getOwner().getEmail();
									out.println("<input type=\"hidden\" name=\"owner\" value=\"" + email + "\">");

									// End HTML.
									out.println("</div>");
									out.println("</form>");
									out.println("</tr>");
								}
							}
						%>
					</table>
				</c:if>
			</div>
		</div>
	</body>
</html>
