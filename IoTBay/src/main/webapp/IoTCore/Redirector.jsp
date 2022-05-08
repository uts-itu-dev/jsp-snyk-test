<%-- 
    Document   : Redirector
    Created on : May 8, 2022, 2:27:55 PM
    Author     : Michael Wu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Please Wait...</title>
		<link rel="stylesheet" href="IoTBayStyles.css">
		<meta http-equiv="refresh" content="1.5; URL=../index.jsp"/>

	</head>
	<body>
		<%
			String H1 = request.getParameter("HeadingMessage");
			if (H1 != null)
				out.println("<h1>"+H1+"</h1>");
			String M = request.getParameter("Message");
			if (M != null)
				out.println("<p class=\"text textArea\">" + M + "</p>");
		%>
	</body>
</html>
