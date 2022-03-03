<%@page import="com.assignment3.model.MemberClass_H"%>
<%@page import="com.assignment3.dao.MemberDaoClass_H"%>
<%@page import="com.assignment3.model.BookClass_H"%>
<%@page import="com.assignment3.dao.BookDaoClass_H"%>
<%@page import="com.assignment3.dao.PublisherDaoClass_H"%>
<%@page import="com.assignment3.model.PublisherClass_H"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Books</title>
<style>

table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>

<body align="center">
	<div style="text-align: center;">
		<h1>Manage Books</h1>
	</div>
	<div>
		<form action="BookServlet" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ADD">Add a Book</option>
							<option value="UPDATE">Update a Book</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Book ID [Fill in case of update Only]</td>
					<td><input type="number" name="bookId" /></td>
				</tr>
				<tr>
					<td>Title</td>
					<td><input type="text" name="title" required="required" /></td>
				</tr>
				<tr>
					<td>Author</td>
					<td><input type="text" name="author" required="required" /></td>
				</tr>
				<tr>
					<td>Price</td>
					<td><input type="number" name="price" required="required" /></td>
				</tr>
				<tr>
					<td>Available</td>
					<td>
						<select id="available" name="available" required="required">
							<option value="Y">Yes</option>
							<option value="N">No</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Publisher</td>
					<td>
						<select id="pubId" name="pubId" required="required">
							<%
							PublisherDaoClass_H publisherDao = new PublisherDaoClass_H();
							List<PublisherClass_H> publisherList = publisherDao.getAllPublishers();
							for (PublisherClass_H publisher : publisherList) {
							%>
								<option value="<%= publisher.getPubId() %>"><%= publisher.getName()%> - <%= publisher.getAddress() %></option>
							<% } %>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form>
	</div>
	
	<div>
		<h2>Issue Book</h2>
		<form action="BookServlet" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ISSUE">Issue a Book</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Book</td>
					<td>
						<select id="bookId" name="bookId" required="required">
							<%
							BookDaoClass_H bookDao = new BookDaoClass_H();
							List<BookClass_H> books = bookDao.getAllBooks();
							for (BookClass_H book : books) {
								if(book.getAvailable() == 'Y') {
							%>
								<option value="<%= book.getBookId() %>"><%= book.getTitle()%> - <%= book.getAuthor() %> - $<%= book.getPrice() %></option>
							<% }} %>
						</select>
					</td>
				</tr>
				<tr>
					<td>Member</td>
					<td>
						<select id="membId" name="membId" required="required">
							<%
							MemberDaoClass_H memberDao = new MemberDaoClass_H();
							List<MemberClass_H> members = memberDao.getAllMembers();
							for (MemberClass_H member : members) {
								
							%>
								<option value="<%= member.getMembId() %>"><%= member.getName()%></option>
							<% } %>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form>
	</div>
	
	<div>
		<h2>Delete Book</h2>
		<form action="BookServlet" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="DELETE">Delete a Book</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Book</td>
					<td>
						<select id="bookId" name="bookId" required="required">
							<%
							for (BookClass_H book : books) {
								if(book.getAvailable() == 'Y') {
							%>
								<option value="<%= book.getBookId() %>"><%= book.getTitle()%> - <%= book.getAuthor() %> - $<%= book.getPrice() %></option>
							<% }} %>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>