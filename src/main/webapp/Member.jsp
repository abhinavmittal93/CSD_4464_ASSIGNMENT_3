<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Members</title>
<style>

table.center {
  margin-left: auto; 
  margin-right: auto;
}
</style>
</head>

<body align="center">
	<div style="text-align: center;">
		<h1>Manage Members</h1>
	</div>
	<div>
		<form action="MemberServlet" method="post">
			<table class="center">
				<tr>
					<td>Action</td>
					<td>
						<select id="action" name="action" required="required">
							<option value="ADD">Add a Member</option>
							<option value="UPDATE">Update a Member</option>
						</select>
					</td>
				</tr>
				<tr>
					<td>Member ID [Fill in case of update Only]</td>
					<td><input type="number" name="membId" /></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" required="required" /></td>
				</tr>
				<tr>
					<td>Address</td>
					<td><input type="text" name="address" required="required" /></td>
				</tr>
				<tr>
					<td>Membership Type</td>
					<td>
						<select id="membType" name="membType" required="required">
							<option value="">Select</option>
							<option value="ADULT">Adult Card</option>
							<option value="CHILD">Child Card</option>
							<option value="STUDENT">Student Card</option>
						</select>
					</td>
				</tr>
			</table><br>
			<input type="submit" value="Submit" />
		</form>
	</div>
</body>
</html>