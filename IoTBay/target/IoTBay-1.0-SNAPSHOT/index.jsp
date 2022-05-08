<%-- 
	Document   : index
	Created on : Mar 30, 2022, 5:59:57 PM
	Author     : Michael Wu

	Purpose    : The landing page of IoTBay.
--%>

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
		<link rel="stylesheet" href="IoTCore/IoTBayStyles.css">
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
					IoT Bay | Introduction to Software Development (41025) - Assignment 2: R1
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
					<span class="pink">SAOBAN SALWA HABIB</span> | <span class="darkerPink">14104638</span>
					<br>
					<span class="pink">SEUNGWON OCK</span> | <span class="darkerPink">14109641</span>
					<br>
					<span class="pink">YESEUL SHIN</span> | <span class="darkerPink">13978248</span>
					<br>
					<span class="pink">CHRISTIAN WU</span> | <span class="darkerPink">14147817</span>
					<br>
					<span class="pink">MICHAEL WU</span> | <span class="darkerPink">13938903</span>
					<br>
					<span class="pink">JERRY YAU</span> | <span class="darkerPink">14150371</span>
				</p>
			</div>
		</div>
		<br><br>
		<h1>Our Products</h1>

		<div class="tileGrid">
			<% int productID = 1; %> <!-- Keep track of Product index when looping. -->
			<c:if test="${Products != null}"> <!-- If session.getAttribute("Products") exists (if there are products). -->
				<c:forEach var="p" items="${Products}"> <!-- Loop over Products. -->
					<div class="tileSpace revealParent">
						<div class="tileTitle">
							<c:out value="${p.name}"/></div>
						<div style="text-align:center; bottom:100%;">
							<fmt:formatNumber value="${p.price}" type="currency"/></div>
						<br><br>
						<div class="hoverRevealer">
							<div class="productmisc revealContent">
								<c:out value="${p.description}"/>
								<br><br>
								<c:choose>
									<c:when test="${User != null}"> <!-- if (session.getAttribute("User") != null) -->
										<%
											User U = (User) session.getAttribute("User");

											// If the User is a Customer, they can add it to their cart.
											if (U.getType() == EUserType.CUSTOMER) {
												String link = "AddToCart?bAnonymous=false&productID=" + productID;
												out.println(
													"<a href=\"" + link + "\">"
													+ "<input class=\"button\" type=\"submit\" value=\"Add to Cart!\">"
													+ "</a>");
												productID++;
											} // If the User is Staff, they can edit the Product.
											else if (U.getType() == EUserType.STAFF) {
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
											// Same link as a Registered Customer, but mark bAnonymous.
											String link = "AddToCart?bAnonymous=true&productID=" + productID;
											out.println(
												"<a href=\"" + link + "\">"
												+ "<input class=\"button\" type=\"submit\" value=\"Add to Cart!\">"
												+ "</a>");
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

	</body>
</html>

