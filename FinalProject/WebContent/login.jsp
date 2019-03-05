<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<h1>Login</h1>

<form method="post" action="http://localhost:8080/FinalProject/controller/User/login">
	<table>
		<tbody>
			<tr>
				<td>User name:</td>
				<td><input type="text" name="username" size="50" /></td>
			</tr>
	
			<tr>
				<td>Password:</td>
				<td><input type="text" name="password" size="50" /></td>
			</tr>
			
			<tr>
				<td>
					<input type=submit />
				</td>
			</tr>
		</tbody>
	</table>
</form>

<p>${message}</p>

</body>
</html>