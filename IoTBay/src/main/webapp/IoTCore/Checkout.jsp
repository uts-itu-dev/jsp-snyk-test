<%-- 
    Document   : Checkout
    Created on : May 13, 2022, 12:12:37 PM
    Author     : Michael Wu
--%>

<%@page import="DAO.DBManager"%>
<%@page import="Model.IoTBay.Person.PaymentInformation"%>
<%@page import="Model.IoTBay.Person.Address"%>
<%@page import="Model.IoTBay.Person.Customer"%>
<%@page import="DAO.*"%>
<%@page import="Model.IoTBay.Core.IoTWebpageBase"%>
<%@page import="Model.IoTBay.Person.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>JSP Page</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
	</head>
	<body>

		<%
			User active = (User) session.getAttribute("User");
			if (active != null)
			{
				IoTWebpageBase.uDB.injectOLI(active.getEmail());
			}
			else
			{
				IoTWebpageBase.uDB.injectOLIAnonymous();
			}

			session.setAttribute("Cart", IoTWebpageBase.uDB.cart);
		%>

		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../index.jsp">Home</a></div>
					<%
						if (active != null)
						{
							out.println("<div class=\"navLinks right\"><a href=\"../Logout\">Logout</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Profile.jsp\">Profile</a></div>");
						}
						else
						{
							out.println("<div class=\"navLinks right\"><a href=\"Register.jsp\">Register</a></div>");
							out.println("<div class=\"navLinks right\"><a href=\"Login.jsp\">Login</a></div>");
						}
					%>
				</div>
			</nav>

			<div class="centreScreen">
				<h1>Checkout</h1>

				<%
					// Get Email or Nothing.
					String email;

					// Find Address Details or Nothing.
					String stNo, stNa, sub, pc, city;

					// Find Payment Information or Nothing.
					String cn, cvv, ch;

					String isCustomer = "";

					// If Unregistered.
					if (active == null)
					{
						stNo = stNa = sub = pc = city = "";
						cn = cvv = ch = "";

						isCustomer = "no";
						email = DBManager.AnonymousUserEmail;
					}
					// If Registered
					else
					{
						Customer c = (Customer) active;

						Address a = c.getAddress();
						PaymentInformation p = c.getPayment();

						stNo = a.getNumber();
						stNa = a.getStreetName();
						sub = a.getSuburb();
						pc = a.getPostcode();
						city = a.getCity();

						cn = p.getCardNo();
						cvv = p.getCVV();
						ch = p.getCardHolder();

						isCustomer = "yes";
						email = c.getEmail();
					}
				%>

				<div class="form-container">
					<div class="form-inner">
						<form action="../Checkout" method="POST">
							<div style="width: 100%; display: table;">
								<div style="display: table-row;">
									<div style="width: 600px; display:table-cell; padding-left:0px">
										<br><br>
										<h2>Delivery Address</h2>

										<table>
											<tr>
												<td>
													<label for="addNum">Street Number:</label>
													<div class="field"><input type="number" placeholder="Street Number" name="addNum" required value=<%=stNo%>></div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="addStreetName">Street:</label>
													<div class="field"><input type="text" placeholder="Street" name="addStreetName" required value=<%=stNa%>></div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="addSuburb">Suburb:</label>
													<div class="field"><input type="text" placeholder="Suburb" name="addSuburb" required value=<%=sub%>></div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="addPostcode">Postcode:</label>
													<div class="field"><input type="number" placeholder="Postcode" name="addPostcode" required value=<%=pc%>></div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="addCity">City:</label>
													<div class="field"><input type="text" placeholder="City" name="addCity" required value=<%=city%>></div>
												</td>
											</tr>
										</table>
									</div>

									<div style="width: 600px; display:table-cell; padding-left:0px">
										<br><br>
										<h2>Payment Information</h2>
										<table>
											<tr>
												<td>
													<label for="CardNo">Card Number:</label>
													<div class="field">
														<input type="number" placeholder="Card Number" name="CardNo" required value=<%=cn%>>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="CVV">CVV:</label>
													<div class="field"><input type="number" placeholder="CVV" name="CVV" required value=<%=cvv%>>
													</div>
												</td>
											</tr>
											<tr>
												<td>
													<label for="CardHolder">Card Holder's Name:</label>
													<div class="field">
														<input type="text" placeholder="Card Holder" name="CardHolder" required value=<%=ch%>>
													</div>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</div>

							<div class="field" style="padding-right:25%">
								<input type="submit" value="Proceed With Checkout" style="float:right; width:20%;">
								
								<input type="hidden" name="isCustomer" value=<%=isCustomer%>>
								<input type="hidden" name="email" value=<%=email%>>
							</div>

						</form>
					</div> <!-- vv -->
				</div> <!-- <div class="form-container"><div class="form-inner"> -->
			</div>
		</div>
	</body>
</html>
