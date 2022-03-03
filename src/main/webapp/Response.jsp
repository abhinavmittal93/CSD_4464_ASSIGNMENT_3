<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Response</title>
</head>
<body>
	<h3>
		<% if (request.getAttribute("message") != null) { %>
			<%=request.getAttribute("message")%>
		<% } else { %>
			No Message to be displayed.
		<% } %>
	</h3>
</body>
</html>