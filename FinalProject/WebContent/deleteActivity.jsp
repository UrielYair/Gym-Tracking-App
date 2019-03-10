<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete activity</title>
</head>
<body>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<button type="submit" name ="button" value = "home">Home</button>
</form>
<br/>
<br/>
<h1>Delete activity</h1>

<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/delete">
	<table>
		<tbody>
			<tr>
				<td>Exercise Name:</td>
				<td>
					<select name="exercise_name">
						<option>abs</option>
						<option>back</option>
						<option>cardio</option>
						<option>chest</option>
						<option>legs</option>
						<option>shoulders</option>
					</select>
			</tr>
	
			<tr>
				<td>Exercise data (dd/MM/yyyy):</td>
				<td><input type="text" name="activityDate" size="50" /></td>
			</tr>

		</tbody>
	</table>
</form>

<p>${message}</p>

</body>
</html>