<%@page import="com.assignment3.model.BookClass_H"%>
<%@page import="java.util.List"%>
<%@page import="com.assignment3.dao.BookDaoClass_H"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Borrowed Books Details</title>
<style>
table, th, td {
  border:1px solid black;
  border-collapse: collapse;
}
table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>
<body>
	<div style="text-align: center;">
		<h1>Borrowed Books Details</h1>
	</div>
	<div>
		<table class="center" style="width:100%">
			<tr>
				<th>Book Title</th>
				<th>Book Author</th>
				<th>Book Price</th>
				<th>Book Publisher</th>
				<th>Issue Date</th>
				<th>Due Date</th>
				<th>Return Date</th>
				<th>Member Name</th>
				<th>Member Address</th>
				<th>Membership Type</th>
			</tr>
			<%
			BookDaoClass_H bookDao = new BookDaoClass_H();
			List<BookClass_H> books = bookDao.getIssuedBooks();
			for (BookClass_H book : books) {
			%>
				<tr>
					<td><%= book.getTitle()%></td>
					<td><%= book.getAuthor()%></td>
					<td>$<%= book.getPrice()%></td>
					<td><%= book.getPublisherClass_H().getName()%></td>
					<td><%= book.getIssueDate()%></td>
					<td><%= book.getDueDate()%></td>
					<td><%if(book.getReturnDate() != null) { out.print(book.getReturnDate()); }%></td>
					<td><%= book.getMemberClass_H().getName()%></td>
					<td><%= book.getMemberClass_H().getAddress()%></td>
					<td><%= book.getMemberClass_H().getMembType()%></td>
				</tr>
			<%
			}
			%>
		</table>
	</div>
</body>
</html>