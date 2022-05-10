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
		<title>Your Profile</title>
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../Logout">Logout</a></div>
					<!-- Already in Profile, so we don't need to link it. -->
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Your Profile</h1><br><br>

				<!-- If the user's current password didn't match, show the message. -->
				<p style="text-align:center;">
					<%
						String err = request.getParameter("err");
						if (err != null) {
							out.println("<span style=\"color:red\"><br>" + err + "</span>");
						}
						
						String upd = request.getParameter("upd");
						if (upd != null) {
							out.println("<span style=\"color:green\"><br>" + upd + "</span>");
						}
					%>
				</p>
				<%
					User base = (User) session.getAttribute("User");
					
					if (base.getType() == EUserType.CUSTOMER) {
						// If logged attribute "User" is a Registered Customer.
						Customer active = (Customer) base;

						if (active != null) {
							PaymentInformation pi = active.getPayment();
							String cn = "";
							String cvv = "";
							String ch = "";
							
							if (pi != null){
								cn = pi.getCardNo();
								if (cn == null)
									cn = "";
								cvv = pi.getCVV();
								if (cvv == null)
									cvv = "";
								ch = pi.getCardHolder();
								if (ch == null)
									ch = "";
							}
						
							out.println(
								// First and Last Names.
								"<div style=\"width: 100%; display: table;\">"
								+ "<div style=\"display: table-row\">"
								
								+ "<div style=\"width: 600px; display:table-cell; padding-left:0px\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"First\">First Name:</label><div class=\"field\"><input type=\"text\" placeholder=\"First Name\" name=\"First\" value=\"" + active.getFirstName() + "\"></div></td></tr>"
								+ "<tr><td><label for=\"Last\">Last Name:</label><div class=\"field\"><input type=\"text\" placeholder=\"Last Name\" name=\"Last\" value=\"" + active.getLastName() + "\"></div></td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Names\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Passwords.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"Password\">Current Password:</label>"
								+ "<div class=\"field\"><input type=\"password\" placeholder=\"Current Password\" name=\"Password\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"Pass1\">New Password:</label>"
								+ "<div class=\"field\"><input type=\"password\" placeholder=\"New Password\" name=\"Pass1\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"Pass2\">Confirm Password:</label>"
								+ "<div class=\"field\"><input type=\"password\" placeholder=\"Confirm Password\" name=\"Pass2\"></div>"
								+ "</td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Password\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Email Address.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"Email\">Email Address:</label>"
								+ "<div class=\"field\"><input type=\"email\" placeholder=\"E-Mail Address\" name=\"Email\" value=\"" + active.getEmail() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Email\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Phone Number.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"PhoneNumber\">Phone Number:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Phone Number\" name=\"PhoneNumber\" value=\"" + active.getPhoneNumber() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"PhoneNumber\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Address Details.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"addNum\">Street Number:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Street Number\" name=\"addNum\" value=\"" + active.getAddress().getNumber() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"addStreetName\">Street:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Street\" name=\"addStreetName\" value=\"" + active.getAddress().getStreetName() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"addSuburb\">Suburb:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Suburb\" name=\"addSuburb\" value=\"" + active.getAddress().getSuburb() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"addPostcode\">Postcode:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Postcode\" name=\"addPostcode\" value=\"" + active.getAddress().getPostcode() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><label for=\"addCity\">City:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"City\" name=\"addCity\" value=\"" + active.getAddress().getCity() + "\"></div>"
								+ "</td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Address\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Payment Information Details.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\" style=\"width:100%\"><table>"
								+ "<tr><td><label for=\"CardNo\">Card Number:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Card Number\" name=\"CardNo\" value=\"" + cn + "\"></td></tr></div>"
								+ "<tr><td><label for=\"CVV\">CVV:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"CVV\" name=\"CVV\" value=\"" + cvv + "\"></td></tr></div>"
								+ "<tr><td><label for=\"CardHolder\">Card Holder's Name:</label>"
								+ "<div class=\"field\"><input type=\"text\" placeholder=\"Card Holder\" name=\"CardHolder\" value=\"" + ch + "\"></td></tr></div>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"PaymentInformation\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"yes\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div></div>"
								+ "</div>"
								+ "</div>"
								+ "</div></div>"
							);
						}
					} else if (base.getType() == EUserType.STAFF) {
						Staff active = (Staff) base;
						if (active != null) {
							out.println(
								// First and Last Names.
								"<div style=\"width: 100%; display: table;\">"
								+ "<div style=\"display: table-row\">"
								
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td><label for=\"First\">First Name:</label><div class=\"field\"><input type=\"text\" placeholder=\"First Name\" name=\"First\" value=\"" + active.getFirstName() + "\"></div></td></tr>"
								+ "<tr><td><label for=\"Last\">Last Name:</label><div class=\"field\"><input type=\"text\" placeholder=\"Last Name\" name=\"Last\" value=\"" + active.getLastName() + "\"></div></td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Names\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"no\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Passwords.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td><label for=\"Password\">Current Password:</label><div class=\"field\"><input type=\"password\" placeholder=\"Current Password\" name=\"Password\"></div></td></tr>"
								+ "<tr><td><label for=\"Pass1\">New Password:</label><div class=\"field\"><input type=\"password\" placeholder=\"New Password\" name=\"Pass1\"></div></td></tr>"
								+ "<tr><td><label for=\"Pass2\">Confirm Password:</label><div class=\"field\"><input type=\"password\" placeholder=\"Confirm Password\" name=\"Pass2\"></div></td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Password\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"no\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>"
								
								// Email Address.
								+ "<div style=\"width: 600px; display:table-cell;\">"
								+ "<div class=\"form-container\"><div class=\"form-inner\">"
								+ "<form action=\"../Update\" method=\"POST\"><table>"
								+ "<tr><td><label for=\"Email\">Email Address:</label><div class=\"field\"><input type=\"email\" placeholder=\"E-Mail Address\" name=\"Email\" value=\"" + active.getEmail() + "\"></div></td></tr>"
								+ "<tr><td><div class=\"field\"><input type=\"submit\" value=\"Update\"></div></td><td>"
								+ "<input type=\"hidden\" name=\"Attribute\" value=\"Email\">"
								+ "<input type=\"hidden\" name=\"bIsCustomer\" value=\"no\">"
								+ "</td></tr></table></form></div></div>"
								+ "</div>");
						}
					}
				%>
			</div>
		</div>
	</body>
</html>
