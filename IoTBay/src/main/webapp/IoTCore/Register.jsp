<%-- 
	Document   : Register
	Created on : 16/03/2022, 10:29:03 AM
	Author     : Michael Wu

	Purpose	   : Allow the user to register an account with IoTBay.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>IoTBay | Register</title>
	</head>
	<body>
		<div class="wrapper">
			<div class="title-text">
				<div class="title login">Register</div>
			</div>

			<div class="form-container">
				<div class="form-inner">
					<form action="../Register" class="login">

						<div class="field">
							<input type="text" placeholder="First Name" name="First" required>
						</div>

						<div class="field">
							<input type="text" placeholder="Last Name" name="Last" required>
						</div>

						<div class="field">
							<input type="text" placeholder="E-Mail Address" name="Email" required>
						</div>

						<div class="field">
							<input type="password" placeholder="Password" name="Pass1" required>
						</div>

						<div class="field">
							<input type="password" placeholder="Confirm Password" name="Pass2" required>
						</div>

						<div class="field">
							<input type="submit" value="Register">
						</div>

						<div class="signup-link">
							Already Registered? <a href="Login.jsp">Login Here</a>.
						</div>

					</form>
				</div>
			</div>
		</div>
	</body>
</html>
