<%-- 
	Document   : index
	Created on : Mar 30, 2022, 5:59:57 PM
	Author     : Michael Wu

	Purpose    : The landing page of IoTBay.
--%>

<%@page import="DAO.DBManager"%>
<%@page import="Model.IoTBay.Person.User.EUserType"%>
<%@page import="Model.IoTBay.Person.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.IoTBay.Product"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>IoTBay</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head>
	<body>
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
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<%
						User U = (User) session.getAttribute("User");
						if (U != null)
						{
							out.println("<div class=\"navLinks right\"><a href=\"Logout\">Logout</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Profile.jsp\">Profile</a></div>");
							if (U.getType() == EUserType.STAFF)
							{
								// If this User is STAFF, show a link to their Control Panel.
								out.println("<div class=\"navLinks right\"><a href=\"StaffControlPanel.jsp\">Staff Control Panel</a></div>");
							}
						}
						else
						{
							// The User is not logged in, or not registered.
							out.println("<div class=\"navLinks right\"><a href=\"Register.jsp\">Register</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Login.jsp\">Login</a></div>");
						}

						if (U == null || U.getType() != EUserType.STAFF)
						{
							// If the User is a Customer, i.e., not Staff, they have a Cart and their existing Orders.
							out.println("<div class=\"navLinks right\"><a href=\"Cart.jsp\">Cart</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Orders.jsp\">Your Orders</a></div>");
						}
					%>

				</div>
			</nav>

			<h1 class="IndexH1 ">Search For Products</h1>

			<!-- If the user of email and password does not exist, show the message. -->
			<p style="text-align:center; color:red">
				<%
					String err = request.getParameter("err");
					if (err != null)
					{
						out.println("<br>" + err);
					}
				%>
			</p>

			<%
				String search = request.getParameter("param");
				if (search != null)
				{
			%>
			<p style="text-align:center; color:green;">Search results for: <%=search%></p>
			<%
				}
			%>
		</div>
		<br><br>

		<div class="form-container">
			<div class="form-inner">
				<form action="../SearchProduct" class="login" method="POST">
					<table style="width:550px;">
						<tr>
							<td>
								<div class="field">
									<input type="text" name="Params" placeholder="Search..."">
								</div>
							</td>
							<td>
								<div class="field">
									<input type="submit" value="Search" style="width:150px;">
								</div>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>

		<div class="tileGrid">
			<!-- Keep track of Product index when looping. -->
			<!-- A crude workaround to tracking Product IDs for button links. -->
			<% int productID = 1; %>
			<% final int iterationsBeforeRejection = 50; %>
			<% int currentIteration = 0; %>

			<!-- If session.getAttribute("Products") exists (if there are products). -->
			<c:if test="${Searched != null}">

				<!-- Loop over Products. -->
				<c:forEach var="p" items="${Searched}">

					<!-- Relevant styles to prepare for mouse hover events. -->
					<div class="tileSpace revealParent">

						<div class="tileTitle">
							<c:out value="${p.name}"/></div>

						<div style="text-align:center; bottom:100%;">
							<fmt:formatNumber value="${p.price}" type="currency"/></div>

						<div style="text-align:center; bottom:100%;">
							<c:out value="${p.quantity}"/> in Stock</div>

						<!-- Anything in this Division will appear when hovered over. -->
						<div class="hoverRevealer">

							<div class="productmisc revealContent">

								<c:out value="${p.description}"/>
								<br><br>

								<%
									Product feProduct;

									// Continue looping through the Products array, even if null was found.
									while ((feProduct = IoTWebpageBase.uDB.findProduct(productID)) == null)
									{
										++productID;
										++currentIteration;

										// We have *actually* reached the end of the array if the ProductID
										// doesn't refer to any Product iterationsBeforeRejection times in
										// a row - there are no more Products.
										if (currentIteration >= iterationsBeforeRejection)
										{
											return;
										}
									}

									currentIteration = 0;
								%>

								<c:choose>
									<c:when test="${User != null}"> <!-- if (session.getAttribute("User") != null) -->
										<%
											// If the User is a Customer, they can add it to their cart.
											if (U.getType() == EUserType.CUSTOMER)
											{
												if (feProduct.getQuantity() > 0)
												{
													out.println("<form action=\"AddToCart\" method=\"POST\">"
														+ "<input class=\"button\" type=\"submit\" value=\"Add to Cart!\">"
														+ "<input type=\"hidden\" name=\"bAnonymous\" value=\"false\">"
														+ "<input type=\"hidden\" name=\"productID\" value=\"" + productID + "\"></form>");
												}
												else
												{
													// No more stock.
													out.println("<form>"
														+ "<input class=\"disabledButton\" value=\"No more Stock!\" disabled>"
														+ "</form>");
												}

												productID++;
											}
											// If the User is Staff, they can edit the Product.
											else if (U.getType() == EUserType.STAFF)
											{
												String link = "ProductEditor?bAnonymous=false&productID=" + productID;
												out.println(
													"<a href=\"" + link + "\">"
													+ "<input class=\"button\" type=\"submit\" value=\"Edit Product\">"
													+ "</a>");
												productID++;
											}
										%>
									</c:when>
									<c:when test="${User == null}"> <!-- if (session.getAttribute("User") == null) -->
										<!-- The 'User' is Anonymous. -->
										<%
											if (feProduct.getQuantity() > 0)
											{
												// Same link as a Registered Customer, but mark bAnonymous.
												out.println("<form action=\"AddToCart\" method=\"POST\">"
													+ "<input class=\"button\" type=\"submit\" value=\"Add to Cart!\">"
													+ "<input type=\"hidden\" name=\"bAnonymous\" value=\"true\">"
													+ "<input type=\"hidden\" name=\"productID\" value=\"" + productID + "\"></form>");
											}
											else
											{
												// No more stock.
												out.println("<form>"
													+ "<input class=\"disabledButton\" value=\"No more Stock!\" disabled>"
													+ "</form>");
											}

											productID++;
										%>
									</c:when>
								</c:choose>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
		<br><br><br>
	</body>
</html>

