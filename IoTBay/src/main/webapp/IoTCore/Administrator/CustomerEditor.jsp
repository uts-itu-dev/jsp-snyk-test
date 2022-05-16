<%-- 
    Document   : AdminAddCustomer
    Created on : May 15, 2022, 3:03:39 PM
    Author     : Michael Wu
--%>

<%@page import="Model.IoTBay.Person.PaymentInformation"%>
<%@page import="Model.IoTBay.Person.Address"%>
<%@page import="Model.IoTBay.Person.Customer"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin | Customer Editor</title>
		<link rel="stylesheet" href="AdministratorStyles.css">
	</head>
	<body>

		<%
			String em = request.getParameter("Email");
			Customer c = IoTWebpageBase.uDB.findCustomer(em);

			if (c == null)
			{
				response.sendRedirect("Admin.jsp?msg=No Customer exists with this email: " + em);
				return;
			}

			String fn, ln, pn, sNo, sNa, sub, pc, ct, cno, cvv, cho;
			
			fn = c.getFirstName();
			ln = c.getLastName();
			pn = c.getPhoneNumber();

			Address a = c.getAddress();
			sNo = a.getNumber();
			sNa = a.getStreetName();
			sub = a.getSuburb();
			pc = a.getPostcode();
			ct = a.getCity();
			
			PaymentInformation p = c.getPayment();
			cno = p.getCardNo();
			cvv = p.getCVV();
			cho = p.getCardHolder();
			
		%>

		<form action="../../AdminAllCustomers" method="POST">
			<div style="padding-left:5%; padding-top:5%;">
				<h1>Customer Editor</h1>

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


				<p class="text">Phone Number: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="number" name="pn" placeholder="Phone Number" value="<%=pn%>">
				</div>

				<br><br>


				<p class="text">Street Number: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="number" name="sNo" placeholder="Street Number" value="<%=sNo%>">
				</div>

				<p class="text">Street Name: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="sNa" placeholder="Street Name" value="<%=sNa%>">
				</div>

				<p class="text">Suburb: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="sub" placeholder="Suburb" value="<%=sub%>">
				</div>

				<p class="text">Postcode: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="number" name="pc" placeholder="Postcode" value="<%=pc%>">
				</div>

				<p class="text">City: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="ct" placeholder="City" value="<%=ct%>">
				</div>
				
				<br><br>


				<p class="text">Card Number: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="number" name="sNo" placeholder="Street Number" value="<%=cno%>">
				</div>

				<p class="text">CVV: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="sNa" placeholder="Street Name" value="<%=cvv%>">
				</div>

				<p class="text">Card Holder: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="sub" placeholder="Suburb" value="<%=cho%>">
				</div>

				<br><br>
				<br><br>

				<input style="background-color:rgba(0,0,.5,.5); border:none" class="link cap" type="submit" value="Update Customer" name="bEdited">
				<input type="hidden" name="Email" value="<%=em%>">

				<br><br>
				<br><br>
				<a href="Admin.jsp" class="link cap" style="padding-bottom:10%">Back</a>
			</div>
		</form>
	</body>
</html>
