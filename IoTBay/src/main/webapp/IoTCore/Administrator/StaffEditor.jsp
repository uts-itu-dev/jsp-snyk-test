<%-- 
    Document   : AdminAddCustomer
    Created on : May 15, 2022, 3:03:39 PM
    Author     : Michael Wu
--%>

<%@page import="Model.IoTBay.Person.Staff"%>
<%@page import="Model.IoTBay.Person.PaymentInformation"%>
<%@page import="Model.IoTBay.Person.Address"%>
<%@page import="Model.IoTBay.Person.Customer"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin | Staff Editor</title>
		<link rel="stylesheet" href="AdministratorStyles.css">
	</head>
	<body>

		<%
			String em = request.getParameter("Email");
			Staff c = IoTWebpageBase.uDB.findStaff(em);

			if (c == null)
			{
				response.sendRedirect("Admin.jsp?msg=No Staff member exists with this email: " + em);
				return;
			}

			String fn, ln;
			
			fn = c.getFirstName();
			ln = c.getLastName();
		%>

		<form action="../../AdminAllStaff" method="POST">
			<div style="padding-left:5%; padding-top:5%;">
				<h1>Staff Editor</h1>

				<p class="text">First Name: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="fn" placeholder="First Name" required value="<%=fn%>">
				</div>

				<p class="text">Last Name: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="ln" placeholder="Last Name" required value="<%=ln%>">
				</div>

				<br><br>

				<p class="text">Email Address: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="email" name="em" placeholder="Email Address" required value="<%=em%>">
				</div>

				<p class="text">Password: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="pw" placeholder="Password">
				</div>

				<br><br>
				<br><br>

				<input style="background-color:rgba(0,0,.5,.5); border:none" class="link cap" type="submit" value="Update Staff Member" name="bEdited">
				<input type="hidden" name="Email" value="<%=em%>">

				<br><br>
				<br><br>
				<a href="Admin.jsp" class="link cap" style="padding-bottom:10%">Back</a>
			</div>
		</form>
	</body>
</html>
