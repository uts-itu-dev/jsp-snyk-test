<%-- 
    Document   : AddProduct
    Created on : May 9, 2022, 4:36:01 PM
    Author     : Michael Wu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Add a Product</title>
		<link rel="stylesheet" href="../IoTBayStyles.css">
	</head>
	<body>
		<div class="IndexDivMain">
			<!-- Top menu bar thing. -->
			<nav>
				<div>
					<div class="navLinks left"><a href="../../index.jsp">Home</a></div>
					<div class="navLinks right"><a href="../../Logout">Logout</a></div>
					<div class="navLinks right"><a href="../Profile.jsp">Profile</a></div>
				</div>
			</nav>
		</div>
		
		<h1>Add Product</h1>
		
		<!-- Show a message notifying the successful inclusion of a Product. -->
		<p style="text-align:center;">
			<%
				String err = request.getParameter("err");
				if (err != null) {
					out.println("<span style=\"color:red\"><br>" + err + "</span>");
				}

				String add = request.getParameter("add");
				if (add != null) {
					out.println("<span style=\"color:green\"><br>" + add + "</span>");
				}
			%>
		</p>
		
		<!-- Pure HTML -->
		<div class="form-container">
			<div class="form-inner">
				<form action="../../AddProduct" class="login" method="POST">
					<label for="ProductName">Product Name:</label> <br>
					<div class="field">
						<input name="ProductName" type="text" placeholder="The name of the new Product" required>
					</div> <br>

					<label for="ProductDesc">Product Description:</label> <br><br>
					<textarea name="ProductDesc" rows="5" cols="50" required></textarea> <br><br>

					<label for="ProductPrice">Product Price:</label> <br>
					<div class="field">
						<input name="ProductPrice" type="number" min =".01" max="9999" step="0.01" required>
					</div> <br>

					<label for="ProductQuant">Quantity:</label> <br>
					<div class="field">
						<input name="ProductQuant" type="number" min ="1" max="9999" step="1" required>
					</div> <br>

					<div class="field">
						<input type="submit" value="Add Product">
					</div>
				</form>
			</div>
		</div>
	</body>
</html>
