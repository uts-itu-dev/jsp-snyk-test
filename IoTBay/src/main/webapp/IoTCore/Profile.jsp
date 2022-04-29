<%-- 
	Document   : Profile
	Created on : Mar 31, 2022, 6:05:20 PM
	Author     : Michael Wu

	Purpose    : Allow the logged in user to see their profile.
--%>

<%@page import="Model.IoTBay.Person.*"%>
<%@page import="Model.IoTBay.Person.User.EUserType"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="IoTBayStyles.css">
		<title>JSP Page</title>
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../Logout">Logout</a></div>
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Your Profile</h1><br><br>

				<!-- If the user's current password didn't match, show the message. -->
				<p style="text-align:center; color:red">
					<%
						String err = request.getParameter("err");
						if (err != null) {
							out.println("<br>" + err);
						}
					%>
				</p>
				<%
					User base = (User) session.getAttribute("User");
					
					if (base.getType() == EUserType.REGISTERED) {
						// If logged attribute "User" is a Registered Customer.
						Customer active = (Customer) base;

						if (active != null) {
							out.println(
								  "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>First Name:</td><td><input type=\"text\" placeholder=\"First Name\" name=\"First\" value=\"" + active.getFirstName() + "\"></td></tr>"
								+ "<tr><td>Last Name:</td><td><input type=\"text\" placeholder=\"Last Name\" name=\"Last\" value=\"" + active.getLastName() + "\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Names\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
								
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>Current Password:</td><td><input type=\"password\" placeholder=\"Current Password\" name=\"Password\"></td></tr>"
								+ "<tr><td>New Password:</td><td><input type=\"password\" placeholder=\"New Password\" name=\"Pass1\"></td></tr>"
								+ "<tr><td>Confirm Password:</td><td><input type=\"password\" placeholder=\"Confirm Password\" name=\"Pass2\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Password\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
								
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>Email Address:</td><td><input type=\"email\" placeholder=\"E-Mail Address\" name=\"Email\" value=\"" + active.getEmail() + "\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Email\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
								
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>Phone Number:</td><td><input type=\"text\" placeholder=\"Phone Number\" name=\"PhoneNumber\" value=\"" + active.getPhoneNumber() + "\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"PhoneNumber\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
								
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>Street Number:</td><td><input type=\"text\" placeholder=\"Street Number\" name=\"addNum\" value=\"" + active.getAddress().getNumber() + "\"></td></tr>"
								+ "<tr><td>Street:</td><td><input type=\"text\" placeholder=\"Street\" name=\"addStreetName\" value=\"" + active.getAddress().getStreetName() + "\"></td></tr>"
								+ "<tr><td>Suburb:</td><td><input type=\"text\" placeholder=\"Suburb\" name=\"addSuburb\" value=\"" + active.getAddress().getSuburb() + "\"></td></tr>"
								+ "<tr><td>Postcode:</td><td><input type=\"text\" placeholder=\"Postcode\" name=\"addPostcode\" value=\"" + active.getAddress().getPostcode() + "\"></td></tr>"
								+ "<tr><td>City:</td><td><input type=\"text\" placeholder=\"City\" name=\"addCity\" value=\"" + active.getAddress().getCity() + "\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Address\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
								
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td>Card Number:</td><td><input type=\"text\" placeholder=\"Card Number\" name=\"CardNo\" value=\"" + (active.getPayment().getCardNo().length() == 0 ? "" : active.getPayment().getCardNo()) + "\"></td></tr>"
								+ "<tr><td>CVV:</td><td><input type=\"text\" placeholder=\"CVV\" name=\"CVV\" value=\"" + (active.getPayment().getCVV().length() == 0 ? "" : active.getPayment().getCVV()) + "\"></td></tr>"
								+ "<tr><td>Card Holder's Name:</td><td><input type=\"text\" placeholder=\"Card Holder\" name=\"CardHolder\" value=\"" + (active.getPayment().getCardHolder().length() == 0 ? "" : active.getPayment().getCardHolder()) + "\"></td></tr>"
								+ "<tr><td></td><td><input type=\"submit\" value=\"Update\">"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"PaymentInformation\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form>"
							);

							out.println("<br>Name: " + active.getFirstName() + " " + active.getLastName());
							out.println("<br>");
							out.println("Email Address: " + active.getEmail());
							out.println("<br>");
							out.println("Address: " + active.getAddress());
						}
					} else if (base.getType() == EUserType.STAFF) {
						Staff active = (Staff) base;
						if (active != null) {

						}
					}
				%>
			</div>
		</div>
	</body>
</html>
