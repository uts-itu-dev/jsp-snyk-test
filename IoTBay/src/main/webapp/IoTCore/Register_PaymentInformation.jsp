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

			<div class="wrapper ForceCentreScreen">
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
								<input type="text" placeholder="Card Number" name="CardNo" <%String cn = request.getParameter("CardNo");
								out.println(cn == null ? "" : "value=" + cn);%> required>
							</div>
							
							<div class="field">
								<input type="text" placeholder="CVV" name="CVV" <%String cvv = request.getParameter("CVV");
								out.println(cvv == null ? "" : "value=" + cvv);%> required>
							</div>
							
							<div class="field">
								<input type="text" placeholder="Card Holder's Name" name="CardHolder" <%String ch = request.getParameter("CardHolder");
								out.println(ch == null ? "" : "value=" + ch);%> required>
							</div>
							
							<div class="field">
								<input type="month" placeholder="Card Holder's Name" name="CardHolder" <%String ex = request.getParameter("Expiry");
								out.println(ex == null ? "" : "value=" + ex);%> min="2022-04" required>
							</div>

							<div class="field">
								<input type="submit" value="Register">
							</div>

							<div class="signup-link">
								Skip? <a href="Register">Skip Entering Payment Information.</a>.
							</div>

						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
