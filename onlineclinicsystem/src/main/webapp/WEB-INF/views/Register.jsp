<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta charset="ISO-8859-1">
<title>Create New Product</title>
</head>
<body>
	<div align="center">	
		<h1>New Registration</h1>
		<br/>
<!-- 		
	private String email;

	private String firstName, lastName, gender, location, city, state, pincode, mobile, password;

	private String dateOfBirth;

	private String Roles; -->
		
		<form action="#" th:action="@{/save}" th:object="${patient}" method="post">
			<table border="0" cellpadding="10">
				<tr>
					<td>First Name:</td>
					<td><input type="text" th:field="*{firstName}" /></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><input type="text" th:field="*{lastName}" /></td>
				</tr>
				<tr>
					<td>Gender:</td>
					<td><input type="text" th:field="*{gender}" /></td>
				</tr>				
				<tr>
					<td>Username:</td>
					<td><input type="text" th:field="*{email}" /></td>
				</tr>				
				<tr>
					<td>Password:</td>
					<td><input type="text" th:field="*{password}" /></td>
				</tr>
				<tr>
					<td>DOB:</td>
					<td><input type="text" th:field="*{dateOfBirth}" /></td>
				</tr>
				<tr>
					<td>Mobile:</td>
					<td><input type="text" th:field="*{mobile}" /></td>
				</tr>
				<tr>
					<td>Address:</td>
					<td><input type="text" th:field="*{location}" /></td>
				</tr>
				<tr>
					<td>City:</td>
					<td><input type="text" th:field="*{city}" /></td>
				</tr>
				<tr>
					<td>State:</td>
					<td><input type="text" th:field="*{state}" /></td>
				</tr>
				<tr>
					<td>ZipCode:</td>
					<td><input type="text" th:field="*{pincode}" /></td>
				</tr>				
				<tr>
					<td colspan="2"><button type="submit">Save</button></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>