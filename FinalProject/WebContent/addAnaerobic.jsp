<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Anaerobic activity</title>
</head>
<body>
<form method="post" action="http://localhost:8080/FinalProject/controller/Navigation/goTo">
<button type = "button" name = "logoutButton" onclick="window.location.href='http://localhost:8080/FinalProject/controller/User/logout'">Logout</button>
<button type="submit" name ="button" value = "home">Home</button>
</form>
<br/>
<br/>
<h1>Add Anaerobic activity</h1>
<p>Activity can be only abs, back, chest, legs or shoulders<p/>
<form method="post" action="http://localhost:8080/FinalProject/controller/Activity/addAnaerobic">
	<table>
		<tbody>
			<tr>
				<td>Exercise Name:</td>
				<td>
					<select name="exercise_name">
						<option>abs</option>
						<option>back</option>
						<option>chest</option>
						<option>legs</option>
						<option>shoulders</option>
					</select>
			</tr>
			
			<tr>
				<td>Amount of sets:</td>
				<td><input type="text" name="amount_of_sets" size="50" /></td>
			</tr>
			
			<tr>
				<td>Repeats:</td>
				<td><input type="text" name="repeats" size="50" /></td>
			</tr>

			<tr>
				<td>Weight:</td>
				<td><input type="text" name="weight" size="50" /></td>
			</tr>

			<tr>
				<td><input type=submit /></td>
			</tr>
		</tbody>
	</table>
</form>

<p>${message}</p>

</body>
</html>