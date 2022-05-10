<%-- 
    Document   : Cart
    Created on : May 10, 2022, 10:06:48 AM
    Author     : Michael Wu
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="Model.IoTBay.Person.User"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
	</head>
	<body>

		<%
			User active = (User) session.getAttribute("User");
			if (active != null) {
				IoTWebpageBase.uDB.injectOLI(active.getEmail());
			}
			else {
				IoTWebpageBase.uDB.injectOLIAnonymous();
			}

				session.setAttribute("Cart", IoTWebpageBase.uDB.cart);
		%>

		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../Logout">Logout</a></div>
					<div class="navLinks right"><a href="Profile.jsp">Profile</a></div>
				</div>
			</nav>
		</div>

		<h1>Your Cart</h1>

		<c:if test="${Cart != null}">
			<table>
				<tr>
					<th>Product Name</th>
					<th>Quantity</th>
					<th>Total Cost</th>
				</tr>

				<c:forEach var="o" items="${Cart}">

					<tr>
						<td><c:out value="${o.product.name}"/></td>
						<td><c:out value="${o.quantity}"/></td>
						<td><fmt:formatNumber value="${o.totalCost}" type="currency"/></td>
					</tr>

				</c:forEach>

			</table>
		</c:if>
	</body>
</html>
