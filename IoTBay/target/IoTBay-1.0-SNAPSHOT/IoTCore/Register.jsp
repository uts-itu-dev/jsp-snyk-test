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
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
				</div>
			</nav>

			<div class="wrapper centreScreen">
				<div class="title-text">
					<div class="title login">Register</div>
				</div>

				<!-- If the user's passwords didn't match, show the message. -->
				<p style="text-align:center; color:red">
					<%
						String err = request.getParameter("err");
						if (err != null) {
							out.println("<br>" + err);
						}
					%>
				</p>

				<div class="form-container">
					<div class="form-inner">
						<form action="../Register" class="login" method="POST">

							<div class="field">
								<input type="text" placeholder="First Name" name="First" <%String f = request.getParameter("First");
								out.println(f == null ? "" : "value=" + f);%> required>
							</div>

							<div class="field">
								<input type="text" placeholder="Last Name" name="Last" <%String l = request.getParameter("Last");
								out.println(l == null ? "" : "value=" + l);%> required>
							</div>

							<div class="field">
								<input type="email" placeholder="E-Mail Address" name="Email" <%String e = request.getParameter("Email");
								out.println(e == null ? "" : "value=" + e);%> required>
							</div>

							<div class="field">
								<input type="text" placeholder="Phone Number" name="PhoneNumber" <%String pn = request.getParameter("PhoneNumber");
								out.println(e == null ? "" : "value=" + pn);%> required>
							</div>

							<div class="field">
								<input type="password" placeholder="Password" name="Pass1" required>
							</div>

							<div class="field">
								<input type="password" placeholder="Confirm Password" name="Pass2" required>
							</div>

							<div class="field">
								<input type="text" placeHolder="Unit/Street No." name="addNum" <%String n = request.getParameter("addNum");
								out.println(n == null ? "" : "value=" + n);%> required>
							</div>

							<div class="field">
								<input type="text" placeHolder="Street" name="addStreetName" <%String sn = request.getParameter("addStreetName");
								out.println(sn == null ? "" : "value=" + sn);%> required>
							</div>

							<div class="field">
								<input type="text" placeHolder="Suburb" name="addSuburb" <%String s = request.getParameter("addSuburb");
								out.println(s == null ? "" : "value=" + s);%> required>
							</div>

							<div class="field">
								<input type="text" placeHolder="Postcode" name="addPostcode" <%String p = request.getParameter("addPostcode");
								out.println(p == null ? "" : "value=" + p);%> required>
							</div>

							<div class="field">
								<input type="text" placeHolder="City" name="addCity" <%String c = request.getParameter("addCity");
								out.println(c == null ? "" : "value=" + c);%> required>
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
		</div>
	</body>
</html>
