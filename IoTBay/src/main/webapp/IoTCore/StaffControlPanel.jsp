<%-- 
    Document   : StaffControlPanel
    Created on : May 9, 2022, 3:47:08 PM
    Author     : Michael Wu
--%>

<%@page import="Model.IoTBay.Person.User.EUserType"%>
<%@page import="Model.IoTBay.Person.User"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Staff Control Panel</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
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
		</div>

		<div class="ForceCentreScreen">
			<h1>Staff Control Panel</h1>
			<br><br>
			<div class="form-container">
				<div class="form-inner">
					<form action="StaffControlPanel/AddProduct.jsp" class="login" method="POST">

						<p>Adds a new Product to IoTBay.</p>
						<div class="field">
							<input type="submit" value="Add Product">
						</div>
					</form>
				</div>
			</div>
			<br><br>
			<div class="form-container">
				<div class="form-inner">
					<form action="StaffControlPanel/SeeEditCustomers.jsp" class="login" method="POST">

						<p>View the details of every Customer registered in IoTBay.</p>
						<div class="field">
							<input type="submit" value="See / Edit Customers">
						</div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>
