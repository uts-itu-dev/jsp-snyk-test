<%-- 
	Document   : Login
	Created on : Mar 30, 2022, 6:37:43 PM
	Author     : Michael Wu

	Purpose    : Provides the interface for the user to log in to IoTBay.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>IoTBay | Login</title>
	</head>
	<body>
		<div class="wrapper">
			<div class="title-text">
				<div class="title login">Login</div>
			</div>

			<div class="form-container">
				<div class="form-inner">
					<form action="../Login" class="login">

						<div class="field">
							<input type="text" placeholder="E-Mail Address" name="Email" required>
						</div>

						<div class="field">
							<input type="password" placeholder="Password" name="Password" required>
						</div>

						<div class="field">
							<input type="submit" value="Login">
						</div>

						<div class="signup-link">
							Not Registered? <a href="Register.jsp">Register Now!</a>
						</div>

					</form>
				</div>
			</div>
		</div>
	</body>
</html>