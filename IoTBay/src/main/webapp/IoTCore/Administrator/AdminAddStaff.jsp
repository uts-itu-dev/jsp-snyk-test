<%-- 
    Document   : AdminAddCustomer
    Created on : May 15, 2022, 3:03:39 PM
    Author     : Michael Wu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Admin | Add Customer</title>
		<link rel="stylesheet" href="AdministratorStyles.css">
	</head>
	<body>
		<form action="../../AdminAddStaff" method="POST">
			<div style="padding-left:5%; padding-top:5%;">
				<h1>Add a Customer</h1>

				<p class="text">First Name: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="fn" placeholder="First Name" required>
				</div>

				<p class="text">Last Name: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="ln" placeholder="Last Name" required>
				</div>

				<br><br>

				<p class="text">Email Address: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="em" placeholder="Email Address" required>
				</div>

				<p class="text">Password: </p>
				<div style="padding-left:5%;">
					<input style="background-color:rgba(0,0,0,0); border:none" class="textInput" type="text" name="pw" placeholder="Password" required>
				</div>

				<br><br>
				<br><br>

				<input style="background-color:rgba(0,0,.5,.5); border:none" class="link cap" type="submit" value="Add Staff Member">

				<br><br>
				<br><br>
				
				<a href="Admin.jsp" class="link cap" style="padding-bottom:10%;">Back</a>
			</div>
		</form>
	</body>
</html>
