<%-- 
    Document   : Admin
    Created on : May 15, 2022, 2:42:48 PM
    Author     : Michael Wu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>IoTBay | Administrator</title>
		<link rel="stylesheet" href="AdministratorStyles.css">
	</head>
	<body>
		<h1 class="C">Administrator Panel</h1>
			<%
				String msg = request.getParameter("msg");
				if (msg != null)
				{
			%>
			<p class="text" style="text-align:center;"><%=msg%></p>
			<%
				}
			%>


		<div class="C" style="padding-top:15%;">
			<a href="AdminAddCustomer.jsp" class="link cap">Add Customer</a>
			<br>
			<a href="ViewAllCustomers.jsp" class="link cap">View all Customers</a>

			<br><br><br><br><br><br>
			<a href="AdminAddStaff.jsp" class="link cap">Add Staff</a>
			<br>
			<a href="ViewAllStaff.jsp" class="link cap">View all Staff</a>

			<br><br><br><br><br><br>
			<a href="../../index.jsp" class="link cap">Return</a>
		</div>
	</body>
</html>
