<%-- 
    Document   : AdminAddCustomer
    Created on : May 15, 2022, 3:03:39 PM
    Author     : Michael Wu
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Model.IoTBay.Person.Customer"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin | View All Customers</title>
		<link rel="stylesheet" href="AdministratorStyles.css">
	</head>
	<body>
		<div style="padding-left:5%; padding-top:5%;">

			<%
				IoTWebpageBase.connectToDB();

				session.setAttribute("UDatabase", IoTWebpageBase.uDB);
				session.setAttribute("Customers", IoTWebpageBase.uDB.customers);
				session.setAttribute("Staff", IoTWebpageBase.uDB.staff);
				session.setAttribute("Products", IoTWebpageBase.uDB.products);
			%>

			<div class="centreScreen">
				<h1>Customers: </h1>
				<form action="../../AdminSearchCustomers" method="POST">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" placeholder="Search Customers" name="Params">
					<input style="background-color:rgba(0,0,.5,.5); border:none" class="link cap" type="submit" value="Search Customers">
				</form>
				
				<br><br>

				<table style="width:90%">
					<tr>
						<th class="text cap bold header">Email</th>
						<th class="text cap bold header">First Name</th>
						<th class="text cap bold header">Last Name</th>
						<th class="text cap bold header">Update/View Details</th>
						<th class="text cap bold header">Remove Customer</th>
					</tr>
					<%
						ArrayList<Customer> customers = (ArrayList<Customer>) session.getAttribute("SearchedCustomers");
						if (customers == null)
						{
						out.println("IST NULLL");
							return;
					}
						for (int i = 0; i < customers.size(); ++i)
						{
							Customer c = customers.get(i);
							String email = c.getEmail();
							String fn = c.getFirstName();
							String ln = c.getLastName();
					%>
					<form action="../../AdminAllCustomers" method="POST">

						<tr>
							<td class="text"><%=email%></td>
							<td class="text"><%=fn%></td>
							<td class="text"><%=ln%></td>

							<td style="vertical-align:middle; text-align:center;"><input class="link" style="padding-bottom:10%; font-size:20px; background-color:rgba(0,0,0,0); border:none;"type="submit" value="Update" name="Update"></td>
							<td style="vertical-align:middle; text-align:center;"><input class="link" style=" padding-bottom:10%; font-size:20px; background-color:rgba(0,0,0,0); border:none;"type="submit" value="Remove" name="Remove"></td>

							<td><input type="hidden" name="Email" value="<%=email%>"></td>
						</tr>

					</form>
					<%						}%>
				</table>
			</div>
			<a href="Admin.jsp" class="link cap" style="padding-bottom:10%;">Back</a>

		</div>
	</body>
</html>
